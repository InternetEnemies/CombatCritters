package com.internetEnemies.combatCritters.presentation;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.Logic.CardDeconstructor;
import com.internetEnemies.combatCritters.Logic.ICardDeconstructor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

import java.io.Serializable;

/**
 * CardDeconstructorPopupFragment.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     16/March/24
 *
 * @PURPOSE:    Popup that occurs when a user tries to sell a card in their inventory. In this
 *              popup the user can choose the quantity of the card they wish to sell (and sell it).
 */
public class CardDeconstructorPopupFragment extends DialogFragment {
    private static final String ARG_KEY = "cardStack";
    private CardDeconstructorPopupFragment.ISellButtonClickListener buttonClickListener;
    private ItemStack<Card> cardStack;
    private int quantityToSell; //Must follow this equality: 1 <= quantityToSell <= cardStack.getAmount()
    private ICardDeconstructor deconstructor;
    private View view;

    public static CardDeconstructorPopupFragment newInstance(ItemStack<Card> stack) {
        CardDeconstructorPopupFragment fragment = new CardDeconstructorPopupFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, stack);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null && cardStack != null) {
            Fragment cardFragment = CardFragment.newInstance(cardStack.getItem());
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.cardFragmentContainer, cardFragment)
                    .commit();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        this.view = inflater.inflate(R.layout.fragment_card_deconstructor_popup, null);
        this.deconstructor = new CardDeconstructor();
        this.quantityToSell = 1; //Default to selling one card.

        initializeCardStack();
        setReceivedView();
        setupNumberChooser();

        builder.setView(view)
                .setPositiveButton("Sell", (dialog, id) -> {
                    if(cardStack != null) {
                        deconstructor.deconstruct(cardStack.getItem(), quantityToSell);
                        buttonClickListener.onSellButtonClicked();
                        Toast.makeText(getContext(), "Sold!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    CardDeconstructorPopupFragment.this.getDialog().cancel();
                });

        return builder.create();
    }

    /**
     * Setup the custom widget that allows users to choose the quantity of card to sell.
     */
    private void setupNumberChooser() {
        if(cardStack != null) {
            TextView numberText = view.findViewById(R.id.numberText);
            ImageButton decrementButton = view.findViewById(R.id.decrementButton);
            ImageButton incrementButton = view.findViewById(R.id.incrementButton);

            decrementButton.setOnClickListener(v -> {
                if (quantityToSell > 1) {
                    quantityToSell--;
                    numberText.setText(String.valueOf(quantityToSell));
                    setReceivedView();
                }
            });

            incrementButton.setOnClickListener(v -> {
                if(quantityToSell < cardStack.getAmount()) {
                    quantityToSell++;
                    numberText.setText(String.valueOf(quantityToSell));
                    setReceivedView();
                }
                else {
                    Toast.makeText(getContext(), "Cannot exceed quantity owned", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Display the amount of currency the user will receive from the sale.
     */
    private void setReceivedView() {
        if(cardStack != null) {
            FrameLayout receivedContainer = view.findViewById(R.id.receivedContainer);

            CurrencyRenderer currencyRenderer = new CurrencyRenderer(new Currency(deconstructor.getResult(cardStack.getItem(), quantityToSell)), getContext());
            View currencyView = currencyRenderer.getView(null, receivedContainer);
            TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
            currencyTextView.setTextColor(Color.BLACK);

            receivedContainer.removeAllViews();
            receivedContainer.addView(currencyView);
        }
    }
    /**
     * Helper function for initializing cardStack from the serializable argument passed to this fragment.
     */
    private void initializeCardStack() {
        if(getArguments() != null) {
            Serializable cardStackSerializable = getArguments().getSerializable(ARG_KEY);
            if(cardStackSerializable instanceof ItemStack<?>) {
                ItemStack<?> itemStack = (ItemStack<?>)cardStackSerializable;
                if(itemStack.getItem() instanceof Card) {
                    cardStack = (ItemStack<Card>)itemStack;
                }
            }
        }
        else {
            cardStack = null;
        }
    }

    public void setSellButtonClickListener(CardDeconstructorPopupFragment.ISellButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    /**
     * @PURPOSE:     Callback for handling sell button clicks.
     */
    public interface ISellButtonClickListener {
        /**
         * Perform some action when sell button is clicked.
         */
        void onSellButtonClicked();
    }
}
