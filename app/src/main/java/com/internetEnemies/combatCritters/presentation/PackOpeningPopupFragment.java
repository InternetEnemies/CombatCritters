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
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;

import java.util.List;

/**
 * PackOpeningPopupFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     14-March-2024
 *
 * @PURPOSE:     Popup that occurs when a pack in PackOpeningActivity is clicked. This popup displays
 *               the set list of cards in the pack that was clicked and provides a button to open
 *               the pack.
 */
public class PackOpeningPopupFragment extends DialogFragment {
    private static final String ARG_KEY = "pack";

    public static PackOpeningPopupFragment newInstance(Pack pack) {
        PackOpeningPopupFragment fragment = new PackOpeningPopupFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_KEY, pack);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_pack_opening_popup, null);

        RecyclerView recyclerView = view.findViewById(R.id.cardRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new SpacingItemDecoration());

        Pack pack;
        if(getArguments() != null)
            pack = (Pack) getArguments().getSerializable(ARG_KEY);
        else
            pack = null;

        if(pack != null) {
            List<Card> cards = pack.getSetList();
            ItemAdapter<Card> cardAdapter = new ItemAdapter<>(CardRenderer.getRenderers(cards, getContext()), null, false);
            recyclerView.setAdapter(cardAdapter);
        }

        builder.setView(view)
                .setPositiveButton("Open pack", (dialog, id) -> {
                    if(pack != null) {
                        Intent intent = new Intent(getActivity(), CardsOpenedActivity.class);
                        intent.putExtra("pack", pack);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    PackOpeningPopupFragment.this.getDialog().cancel();
                });

        return builder.create();
    }
}