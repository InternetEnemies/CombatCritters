package com.internetEnemies.combatCritters.presentation.battles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.internetEnemies.combatCritters.Logic.battles.IBattleOrchestrator;
import com.internetEnemies.combatCritters.Logic.battles.exceptions.BattleInputException;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityBattleBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.presentation.LogicProvider;
import com.internetEnemies.combatCritters.presentation.MainMenuActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * BattleActivity.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2024-03-23
 *
 * @PURPOSE:    Ui for battles
 */
public class BattleActivity extends AppCompatActivity {

    private BattleCardsRowViewModel bufferVM;
    private BattleCardsRowViewModel enemyVM;
    private BattleCardsRowViewModel playerVM;

    private HandPopupViewModel handVM;

    private BattleViewModel viewModel;
    private IBattleOrchestrator battle;

    private ActivityBattleBinding binding;

    private boolean isSacrificing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // setup view models for child states
        bufferVM = getCardRowVM(R.id.enemyBuffer);
        enemyVM = getCardRowVM(R.id.enemyCards);
        playerVM = getCardRowVM(R.id.playerCards);
        handVM = new ViewModelProvider(this).get(HandPopupViewModel.class);

        isSacrificing = false;

        // setup observers
        this.viewModel = new ViewModelProvider(this).get(BattleViewModel.class);
        this.viewModel.getBuffer().observe(this,bufferVM::setCardStates);
        this.viewModel.getEnemy().observe(this,enemyVM::setCardStates);
        this.viewModel.getPlayer().observe(this,playerVM::setCardStates);

        this.viewModel.getPlayerHealth().observe(this, getHealthHandler(R.id.playerHealth));
        this.viewModel.getEnemyHealth().observe(this, getHealthHandler(R.id.enemyHealth));

        this.viewModel.getEnergy().observe(this, this::handleEnergy);

        buttonSetup();
        playerVM.setListener(this::handleCardClick);

        // setup playCard button display
        handVM.getSelected().observe(this, this::handleSelectChange);

        //! PLACEHOLDER SETUP

        Card card = new CritterCard(//todo replace deck with real one, probably need to add it to the DBInit script
                0,
                "TestName",
                "",
                1,
                Card.Rarity.COMMON,
                10,
                10,
                new ArrayList<>()
        );

        List<Card> deck = new ArrayList<>();
        deck.add(card);
        deck.add(card);
        deck.add(card);
        deck.add(card);

        battle = LogicProvider.getInstance().getBattleRegistry().getBattle(this.viewModel, 0, deck);
    }

    /**
     * initialize the button handlers
     */
    private void buttonSetup() {
        binding.buttonPlayCard.setOnClickListener(this::handlePlayCard);
        binding.buttonEndTurn.setOnClickListener(this::handleEndTurn);
        binding.buttonSacrifice.setOnClickListener(this::handleSacrifice);

        binding.buttonBack.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(BattleActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }

    /**
     * handler for when a player card is clicked
     * @param pos position that was clicked
     */
    private void handleCardClick(int pos){
        Card card;
        //* note that the order here is on purpose, sacrificing should proceed attempting to play
        try {
            if (isSacrificing){
                battle.sacrifice(pos);
            } else if ((card = handVM.getSelected().getValue()) != null){
                battle.playCard(pos,card);
                handVM.clearSelected();
            }
        } catch (BattleInputException e) {
            handleException(e);
        }
        // do nothing if the player isn't trying to play a card or sacrifice
    }

    //Buttons

    /**
     * handler for sacrifice button
     */
    private void handleSacrifice(View button) {
        isSacrificing = !isSacrificing;
        updateSacrificeDisplay();
    }

    /**
     * handler for play card button
     */
    private void handlePlayCard(View button) {
        //create hand popup
        HandPopupFragment fragment = HandPopupFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "player_hand");
        handVM.setCards(this.viewModel.getHand());
    }

    /**
     *  handler for end turn button
     */
    private void handleEndTurn(View button) {
        try {
            this.battle.endTurn();
        } catch (BattleInputException e) {
            handleException(e);
        }
    }


    //UI Display Elements

    /**
     * provider for textview callbacks for health
     * @param id id of the ui element to update
     * @return callback that takes an amount to update the health textview to
     */
    private Observer<? super Integer> getHealthHandler(int id) {
        return health-> {
            TextView view = (TextView) findViewById(id);
            view.setText(String.valueOf(health));
        };
    }

    /**
     * handler for when the isSacrificing var changes
     * updates button to display correct state
     */
    private void updateSacrificeDisplay() {
        int id;
        if(isSacrificing) {
            id = R.drawable.skull_active;
        } else {
            id = R.drawable.skull;
        }
        binding.buttonSacrifice.setImageResource(id);
    }

    /**
     * handler for selection in the hand changing
     * changes the button image to the correct state
     * @param selected card that was selected
     */
    private void handleSelectChange(Card selected) {
        int id;
        if(selected == null) {
            id = R.drawable.cardfan;
        } else {
            id = R.drawable.cardfan_active;
        }
        this.binding.buttonPlayCard.setImageResource(id);
    }

    /**
     * handler for changes in the players energy, updates the ui
     * @param energy current player energy
     */
    private void handleEnergy(int energy) {
        ImageView view = findViewById(R.id.energyBar);
        int id;
        switch(energy) {
            case 0:
                id = R.drawable.energy_bar0;
                break;
            case 1:
                id = R.drawable.energy_bar1;
                break;
            case 2:
                id = R.drawable.energy_bar2;
                break;
            case 3:
                id = R.drawable.energy_bar3;
                break;
            case 4:
                id = R.drawable.energy_bar4;
                break;
            case 5:
                id = R.drawable.energy_bar5;
                break;
            default:
                //! this should never be reached but if it is this will handle it gracefully
                if (energy > 5) {
                    id = R.drawable.energy_bar5;
                } else {
                    id = R.drawable.energy_bar0;
                }
        }
        view.setImageResource(id);
    }

    //Helpers

    /**
     * gets the view model for a CardRow
     * @param id id of the CardRowFragment
     * @return view model for the card row
     */
    @NonNull
    private BattleCardsRowViewModel getCardRowVM(int id) {
        BattleCardsRow cardsRow = (BattleCardsRow) getSupportFragmentManager().findFragmentById(id);
        return new ViewModelProvider(cardsRow).get(BattleCardsRowViewModel.class);
    }

    /**
     * out put the exception to the user
     * @param exception exception to display
     */
    private void handleException(BattleInputException exception) {
        Toast.makeText(this, exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}