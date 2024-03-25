package com.internetEnemies.combatCritters.presentation;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.Logic.ITradeUpHandler;
import com.internetEnemies.combatCritters.Logic.TradeUpHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.data.Database;
import com.internetEnemies.combatCritters.databinding.ActivityTradeUpBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.CardStackRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.MysteryCardRenderer;

import java.util.ArrayList;

/**
 * TradeUpActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     24-March-2024
 *
 * @PURPOSE:    Screen for trade ups.
 */
public class TradeUpActivity extends AppCompatActivity {
    ActivityTradeUpBinding binding;
    private ItemAdapter<ItemStack<Card>> inventoryAdapter;
    private ItemAdapter<Card> tradeUpAdapter;
    private ITradeUpHandler tradeUpHandler;
    private static final float SCALE_FACTOR = .65f; //Scale factor for trade up cards


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityTradeUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.tradeUpHandler = LogicProvider.getInstance().getTradeUpHandler();
        this.inventoryAdapter = new ItemAdapter<>(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this), this::inventoryCardClicked, false);
        this.tradeUpAdapter = new ItemAdapter<>(new ArrayList<>(), this::tradeUpCardClicked, false);

        binding.inventoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        binding.inventoryRecyclerView.addItemDecoration(new SpacingItemDecoration());
        binding.inventoryRecyclerView.setAdapter(inventoryAdapter);

        binding.tradeUpRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.tradeUpRecyclerView.addItemDecoration(new SpacingItemDecoration(0, 0));
        binding.tradeUpRecyclerView.setAdapter(tradeUpAdapter);

        binding.tradeUpButton.setOnClickListener(v -> tradeUpButtonClicked());

        binding.mainMenuButton.setOnClickListener((View buttonView) -> {
            Intent intent = new Intent(TradeUpActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });
    }


    private void tradeUpButtonClicked() {
        try {
//            tradeUpHandler.confirmTradeUp();
            Log.d("here", getCardView().toString());
            showCardPopupFragment(getCard());
        }
        catch(Exception e) {

        }
    }

    private void inventoryCardClicked(ItemStack<Card> cardStack) {
        tradeUpHandler.addCard(cardStack.getItem());
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));

        showTradeUpPreview(isValid());
    }

    private void tradeUpCardClicked(Card card) {
        tradeUpHandler.removeCard(card);
        tradeUpAdapter.updateItems(CardRenderer.getRenderers(tradeUpHandler.getSelectedCards(), this, SCALE_FACTOR));
        inventoryAdapter.updateItems(CardStackRenderer.getRenderers(tradeUpHandler.getCards(), this));

        showTradeUpPreview(isValid());
    }

    private void showTradeUpPreview(boolean show) {
        if(show) {
            binding.rightArrow.setVisibility(View.VISIBLE);
            binding.mysteryCardContainer.removeAllViews();
            binding.mysteryCardContainer.addView((new MysteryCardRenderer(tradeUpHandler.getCurrentTradeUpRarity(), this, SCALE_FACTOR)).getView(null, binding.mysteryCardContainer));
            binding.tradeUpButton.setVisibility(View.VISIBLE);
        }
        else {
            binding.rightArrow.setVisibility(View.INVISIBLE);
            binding.mysteryCardContainer.removeAllViews();
            binding.tradeUpButton.setVisibility(View.INVISIBLE);
        }
//        binding.tradeUpButton.setVisibility(View.VISIBLE); //TODO remove this
    }

    private void showCardPopupFragment(Card card) {
        CardPopupFragment fragment = CardPopupFragment.newInstance(card);
        fragment.show(getSupportFragmentManager(), "card_popup");
    }

    //TODO get rid of this and the other
    private boolean isValid() {
        if(tradeUpHandler.getSelectedCards().size() == 5 ) {
            return true;
        }
        return false;
    }

    private Card getCard() {
        return  Database.getInstance().getCardInventory().getCards().get(0).getItem();
    }

    private View getCardView() {
        Card card = Database.getInstance().getCardInventory().getCards().get(0).getItem();
        return (new CardRenderer(card, this).getView(null, null));
    }

    private void showCardPopup() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Custom Dialog")
                .setView(getCardView())
                .setPositiveButton("Close", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .create();

        dialog.show();
    }
}
