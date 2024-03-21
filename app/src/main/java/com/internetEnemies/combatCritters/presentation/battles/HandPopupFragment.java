package com.internetEnemies.combatCritters.presentation.battles;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internetEnemies.combatCritters.R;

public class HandPopupFragment extends DialogFragment {

    private HandPopupViewModel mViewModel;

    public static HandPopupFragment newInstance() {
        return new HandPopupFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_hand_popup, null);
        builder.setView(view)
                .setNegativeButton("Close",(dialog, id) -> dialog.cancel());
        return builder.create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HandPopupViewModel.class);
        // TODO: Use the ViewModel
    }

}