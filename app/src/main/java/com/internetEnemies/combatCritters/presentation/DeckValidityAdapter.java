package com.internetEnemies.combatCritters.presentation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.internetEnemies.combatCritters.R;

import java.util.List;

public class DeckValidityAdapter extends RecyclerView.Adapter<DeckIssueViewHolder> {

    private final List<String> issues;

    public DeckValidityAdapter(List<String> issues) {
        this.issues = issues;
    }

    @NonNull
    @Override
    public DeckIssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deck_issue_item, parent, false);

        return new DeckIssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckIssueViewHolder holder, int position) {
        holder.getIssue().setText(issues.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    @SuppressLint("NotifyDataSetChanged")//IDK how to do this better lol
    public void updateIssues(List<String> issues) {
        this.issues.clear();
        this.issues.addAll(issues);
        this.notifyDataSetChanged();
    }
}
