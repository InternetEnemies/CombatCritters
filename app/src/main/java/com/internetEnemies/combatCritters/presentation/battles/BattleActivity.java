package com.internetEnemies.combatCritters.presentation.battles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.ActivityBattleBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CritterCard;
import com.internetEnemies.combatCritters.objects.battles.CardState;

import java.util.ArrayList;
import java.util.List;

public class BattleActivity extends AppCompatActivity {

    private BattleCardsRowViewModel bufferVM;
    private BattleCardsRowViewModel enemyVM;
    private BattleCardsRowViewModel playerVM;

    private BattleViewModel viewModel;

    private ActivityBattleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBattleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bufferVM = getCardRowVM(R.id.enemyBuffer);
        enemyVM = getCardRowVM(R.id.enemyCards);
        playerVM = getCardRowVM(R.id.playerCards);

        this.viewModel = new ViewModelProvider(this).get(BattleViewModel.class);
        this.viewModel.getBuffer().observe(this,bufferVM::setCardStates);
        this.viewModel.getEnemy().observe(this,enemyVM::setCardStates);
        this.viewModel.getPlayer().observe(this,playerVM::setCardStates);

        this.viewModel.getPlayerHealth().observe(this, getHealthHandler(R.id.playerHealth));
        this.viewModel.getEnemyHealth().observe(this, getHealthHandler(R.id.enemyHealth));

        this.viewModel.getEnergy().observe(this, this::handleEnergy);

        buttonSetup();

        //! PLACEHOLDER SETUP
        CritterCard card = new CritterCard(//todo add some placeholders
                0,
                "TestName",
                "",
                1,
                Card.Rarity.COMMON,
                10,
                10,
                new ArrayList<>()
        );
        CardState testCard = new CardState(15,card);

        List<CardState> testList = new ArrayList<>();
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        viewModel.setBufferCards(testList);
        viewModel.setEnemyCards(testList);
        viewModel.setPlayerCards(testList);

        viewModel.setPlayerHealth(15);
        viewModel.setEnemyHealth(25);
        viewModel.setEnergy(3);
        List<Card> critters = new ArrayList<>();
        critters.add(card);
        critters.add(card);
        critters.add(card);
        critters.add(card);

        viewModel.setHand(critters);

    }

    /**
     * initialize the button handlers
     */
    private void buttonSetup() {
        binding.buttonPlayCard.setOnClickListener(this::handlePlayCard);
    }

    private void handlePlayCard(View button) {
        //create hand popup
        HandPopupFragment fragment = HandPopupFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "player_hand");
        new ViewModelProvider(this).get(HandPopupViewModel.class)
                .setCards(this.viewModel.getHand());
    }

    private Observer<? super Integer> getHealthHandler(int id) {
        return health-> {
            TextView view = (TextView) findViewById(id);
            view.setText(String.valueOf(health));
        };
    }

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

    @NonNull
    private BattleCardsRowViewModel getCardRowVM(int id) {
        BattleCardsRow cardsRow = (BattleCardsRow) getSupportFragmentManager().findFragmentById(id);
        return new ViewModelProvider(cardsRow).get(BattleCardsRowViewModel.class);
    }
}