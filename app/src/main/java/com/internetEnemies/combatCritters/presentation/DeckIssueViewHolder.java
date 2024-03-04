package com.internetEnemies.combatCritters.presentation;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;

public class DeckIssueViewHolder extends RecyclerView.ViewHolder {
    private final TextView issue;
    public DeckIssueViewHolder(@NonNull View itemView) {
        super(itemView);
        issue = itemView.findViewById(R.id.deck_issue);
    }

    public TextView getIssue(){
        return this.issue;
    }
}
