package com.internetEnemies.combatCritters.presentation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.List;

public class MarketplacePopupFragment extends DialogFragment {
    private static final String ARG_KEY = "transaction";

    public static MarketplacePopupFragment newInstance(MarketTransaction transaction) {
        MarketplacePopupFragment fragment = new MarketplacePopupFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, transaction);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_marketplace_popup, null);



        if(getArguments() != null) {

        }

        builder.setView(view)
                .setPositiveButton("Purchase", (dialog, id) -> {
                    MarketTransaction transaction = (MarketTransaction) getArguments().getSerializable(ARG_KEY);
                    if(transaction != null) {

                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    MarketplacePopupFragment.this.getDialog().cancel();
                });

        return builder.create();
    }
}
