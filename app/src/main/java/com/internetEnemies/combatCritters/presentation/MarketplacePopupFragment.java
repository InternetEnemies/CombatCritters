package com.internetEnemies.combatCritters.presentation;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

/**
 * MarketplacePopupFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-January-2024
 *
 * @PURPOSE:     Popup that occurs when a user clicks on an offer in MarketplaceActivity. This popup
 *               displays information on the selected offer and provides a purchase button.
 */
public class MarketplacePopupFragment extends DialogFragment {
    private static final String ARG_KEY = "transaction";
    private IMarketHandler marketHandler;
    private IBuyButtonClickListener buttonClickListener;

    public static MarketplacePopupFragment newInstance(MarketTransaction transaction) {
        MarketplacePopupFragment fragment = new MarketplacePopupFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, transaction);
        fragment.setArguments(args);
        return fragment;
    }

    public void setBuyButtonClickListener(IBuyButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            View view = dialog.findViewById(R.id.fragmentContainer);
            if (view != null) {
                setFrag((MarketTransaction) getArguments().getSerializable(ARG_KEY));
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_marketplace_popup, null);

        marketHandler = new MarketHandler();

        MarketTransaction transaction;
        if (getArguments() != null) {
            transaction = (MarketTransaction) getArguments().getSerializable(ARG_KEY);
        }
        else {
            transaction = null;
        }

        builder.setView(view)
                .setPositiveButton("Purchase", (dialog, id) -> {
                    performTransaction(transaction);
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    MarketplacePopupFragment.this.getDialog().cancel();
                });


        setCostText(transaction, view);

        return builder.create();
    }

    public void setFrag(MarketTransaction transaction) {
        if(transaction != null) {
            Fragment fragment;
            if (transaction.getReceived().size() > 1) { //It's a bundle
                fragment = BundleFragment.newInstance(transaction);
            }
            else if (transaction.getReceivedFirstItem().getItem() instanceof Pack) { //It's a pack
                fragment = PackFragment.newInstance((Pack)transaction.getReceivedFirstItem().getItem());
            }
            else { //It's a card
                fragment = CardFragment.newInstance((Card)transaction.getReceivedFirstItem().getItem());
            }

            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();

        }
    }

    private void setCostText(MarketTransaction transaction, View view) {
        if(transaction != null) {
            FrameLayout balanceContainer = view.findViewById(R.id.balanceContainer);
            CurrencyRenderer currencyRenderer = new CurrencyRenderer(transaction.getPrice(), getContext());
            View currencyView = currencyRenderer.getView(null, balanceContainer);
            TextView currencyTextView = currencyView.findViewById(R.id.currencyTextView);
            currencyTextView.setTextColor(Color.BLACK);
            balanceContainer.addView(currencyView);
        }
    }

    private void performTransaction(MarketTransaction transaction) {
        if (transaction != null) {
            if(marketHandler.performTransaction(transaction)) {
                Toast.makeText(getContext(), "Transaction successful!", Toast.LENGTH_SHORT).show();
                if(buttonClickListener != null) {
                    buttonClickListener.onBuyButtonClicked();
                }
            }
            else {
                Toast.makeText(getContext(), "Not enough money", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @PURPOSE:     Callback for handling buy button clicks.
     */
    public interface IBuyButtonClickListener {
        /**
         * Perform some action when buy button is clicked.
         */
        void onBuyButtonClicked();
    }
}
