package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;

public class BundleViewBuilder {
    private final View bundleView;
    private final Context context;

    public BundleViewBuilder(Context context, ViewGroup parent){
        this.context = context;
        this.bundleView = LayoutInflater.from(this.context).inflate(R.layout.bundle, parent, false);
    }

    public View getBundleView() {return bundleView;}

    public void setContents(String contents) {
        setText(R.id.bundleContents,contents);
    };

    private void setText(int id, String text) {
        TextView view = bundleView.findViewById(id);
        view.setText(text);
    }
}
