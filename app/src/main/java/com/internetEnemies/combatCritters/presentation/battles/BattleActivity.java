package com.internetEnemies.combatCritters.presentation.battles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.internetEnemies.combatCritters.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        bufferVM = getCardRowVM(R.id.enemyBuffer);
        enemyVM = getCardRowVM(R.id.enemyCards);
        playerVM = getCardRowVM(R.id.playerCards);

        this.viewModel = new ViewModelProvider(this).get(BattleViewModel.class);
        this.viewModel.getBuffer().observe(this,bufferVM::setCardStates);
        this.viewModel.getEnemy().observe(this,enemyVM::setCardStates);
        this.viewModel.getPlayer().observe(this,playerVM::setCardStates);

        //! PLACEHOLDER SETUP
        CardState testCard = new CardState(15,new CritterCard(//todo add some placeholders
                0,
                "TestName",
                "",
                1,
                Card.Rarity.COMMON,
                10,
                10,
                new ArrayList<>()
        ));

        List<CardState> testList = new ArrayList<>();
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        testList.add(testCard);
        viewModel.setBufferCards(testList);
        viewModel.setEnemyCards(testList);
        viewModel.setPlayerCards(testList);

    }

    @NonNull
    private BattleCardsRowViewModel getCardRowVM(int id) {
        BattleCardsRow cardsRow = (BattleCardsRow) getSupportFragmentManager().findFragmentById(id);
        return new ViewModelProvider(cardsRow).get(BattleCardsRowViewModel.class);
    }
}