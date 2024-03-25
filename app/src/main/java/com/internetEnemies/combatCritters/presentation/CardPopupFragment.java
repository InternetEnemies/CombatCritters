package com.internetEnemies.combatCritters.presentation;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;

import java.io.Serializable;

/**
 * TradeUpActivity.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     25-March-2024
 *
 * @PURPOSE:    Popup fragment for TradeUpActivity. Popup displays the result of a trade up.
 */
public class CardPopupFragment extends DialogFragment {
    private static final String ARG_KEY = "card";

    public static CardPopupFragment newInstance(Card card) {
        CardPopupFragment fragment = new CardPopupFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, card);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_card_popup, null);

        builder.setView(view)
                .setPositiveButton("Close", (dialog, id) -> dismiss());

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Card card = getCard();
            if (card != null) {
                TextView titleTextView = dialog.findViewById(R.id.titleText);
                String titleText = card.getName() + " has been added to your inventory";
                titleTextView.setText(titleText);
                setFrag(card);
            }
        }
    }

    /**
     * Sets the fragment in CardPopupFragment to display the card
     *
     * @param card card that is used to initialize the fragment.
     */
    public void setFrag(Card card) {
        Fragment fragment = CardFragment.newInstance(card);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    /**
     * @return the card passed in as an argument
     */
    private Card getCard() {
        if (getArguments() != null) {
            Serializable cardSerializable = getArguments().getSerializable(ARG_KEY);
            if (cardSerializable instanceof Card) {
                return (Card) cardSerializable;
            }
        }
        return null;
    }
}
