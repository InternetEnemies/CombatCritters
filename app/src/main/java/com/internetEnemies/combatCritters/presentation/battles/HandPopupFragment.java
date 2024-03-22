package com.internetEnemies.combatCritters.presentation.battles;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.presentation.ItemAdapter;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import java.util.Objects;

public class HandPopupFragment extends DialogFragment {

    private HandPopupViewModel mViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter<Card> adapter;

    public static HandPopupFragment newInstance() {
        return new HandPopupFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        this.mViewModel = new ViewModelProvider(requireActivity()).get(HandPopupViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_hand_popup, null);
        builder.setView(view)
                .setNegativeButton("Close",(dialog, id) -> dialog.cancel());
        recyclerView = view.findViewById(R.id.handRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new ItemAdapter<>(CardRenderer.getRenderers(Objects.requireNonNull(mViewModel.getCards().getValue()), getContext()), this::handleCardClick,true);
        recyclerView.setAdapter(adapter);
        mViewModel.getCards().observe(this, cards -> {
            adapter.updateItems(CardRenderer.getRenderers(cards, getContext()));
        });

        return builder.create();
    }

    public void handleCardClick(Card card) {
        Dialog dialog = getDialog();
        if ( dialog!= null) {
            this.mViewModel.setSelected(card);
            dialog.cancel();
        }
    }
}