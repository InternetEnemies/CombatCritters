package com.internetEnemies.combatCritters.presentation.battles;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        BattleCardsRow buffer = (BattleCardsRow) getSupportFragmentManager().findFragmentById(R.id.enemyBuffer);
        BattleCardsRow enemy = (BattleCardsRow) getSupportFragmentManager().findFragmentById(R.id.enemyCards);
        BattleCardsRow player = (BattleCardsRow) getSupportFragmentManager().findFragmentById(R.id.playerCards);
        bufferVM = new ViewModelProvider(buffer).get(BattleCardsRowViewModel.class);
        enemyVM = new ViewModelProvider(enemy).get(BattleCardsRowViewModel.class);
        playerVM = new ViewModelProvider(player).get(BattleCardsRowViewModel.class);
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
        bufferVM.setCardStates(testList);
        enemyVM.setCardStates(testList);
        playerVM.setCardStates(testList);
    }
}