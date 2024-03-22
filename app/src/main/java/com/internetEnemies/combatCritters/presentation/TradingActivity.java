package com.internetEnemies.combatCritters.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.IPackInventoryManager;
import com.internetEnemies.combatCritters.Logic.ITradesHandler;
import com.internetEnemies.combatCritters.Logic.TradeTransactionBuilder;
import com.internetEnemies.combatCritters.Logic.TradesHandler;
import com.internetEnemies.combatCritters.data.IRegistry;
import com.internetEnemies.combatCritters.data.PackCardDatabase;
import com.internetEnemies.combatCritters.databinding.ActivityMarketplaceBinding;
import com.internetEnemies.combatCritters.databinding.ActivityPackOpeningBinding;
import com.internetEnemies.combatCritters.databinding.ActivityTradingBinding;
import com.internetEnemies.combatCritters.databinding.FragmentCardBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.TradeTransaction;
import com.internetEnemies.combatCritters.presentation.TradeItemAdapter;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;

import java.util.ArrayList;
import java.util.List;

public class TradingActivity extends AppCompatActivity {
    private ActivityTradingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTradingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        TradeTransactionBuilder builder = new TradeTransactionBuilder();
//
//        ItemStack<Currency> currencyStack = new ItemStack<>(new Currency(10), 1);
//
//        List<Card> cards = PackCardDatabase.getInstance().getCardDB().getAll();
//        ItemStack<Card> card1 = new ItemStack<>(cards.get(0), 3);
//        ItemStack<Card> card2 = new ItemStack<>(cards.get(1), 4);
//
//        builder.addToGiven(currencyStack);
//        builder.addToGiven(card1);
//        builder.addToGiven(card2);
//        builder.addToGiven(card2);
//        builder.addToGiven(card2);
//
//        builder.addToReceived(card1);
//        builder.addToReceived(card2);
//        builder.addToReceived(currencyStack);
//        builder.addToReceived(card1);
//        builder.addToReceived(card2);
//        builder.addToReceived(card2);
//
//        TradeTransaction transaction = builder.build();

        ITradesHandler handler = new TradesHandler();
        Log.d("here1", String.valueOf(handler.getOffers().size()));

//        List<TradeTransaction > transactions = new ArrayList<>();
//        transactions.add(transaction);


        TradeTransactionAdapter adapter = new TradeTransactionAdapter(handler.getOffers());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.addItemDecoration(new SpacingItemDecoration());

        binding.recyclerView.setAdapter(adapter);
    }
}