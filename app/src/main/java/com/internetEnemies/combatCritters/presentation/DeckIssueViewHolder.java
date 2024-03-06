package com.internetEnemies.combatCritters.presentation;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;

/**
 * DeckIssueViewHolder.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     3/5/24
 *
 * @PURPOSE:    ViewHolder for the deck issues recycler
 */
public class DeckIssueViewHolder extends RecyclerView.ViewHolder {
    private final TextView issue;
    public DeckIssueViewHolder(@NonNull View itemView) {
        super(itemView);
        issue = itemView.findViewById(R.id.deck_issue);
    }

    /**
     *
     * @return TextView for the issue text
     */
    public TextView getIssue(){
        return this.issue;
    }
}
