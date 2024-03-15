package com.internetEnemies.combatCritters.presentation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.io.Serializable;
import java.util.List;

public class CardDeconstructorPopupFragment extends DialogFragment {
    private static final String ARG_KEY = "cardStack";
    private ItemStack<Card> cardStack;

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
        View view = inflater.inflate(R.layout.fragment_card_deconstructor_popup, null);

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

        if(cardStack != null) {

        }

        builder.setView(view)
                .setPositiveButton("Sell", (dialog, id) -> {
                    if(cardStack != null) {

                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    CardDeconstructorPopupFragment.this.getDialog().cancel();
                });

        return builder.create();
    }

    private void setupNumberChooser() {
        if(cardStack != null) {

        }
    }
}
