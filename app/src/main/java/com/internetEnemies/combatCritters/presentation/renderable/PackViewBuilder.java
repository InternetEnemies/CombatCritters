package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.internetEnemies.combatCritters.R;

public class PackViewBuilder  {
    private final View packView;
    private final Context context;

    public PackViewBuilder(Context context, ViewGroup parent){
        this.context = context;
        this.packView = LayoutInflater.from(this.context).inflate(R.layout.pack, parent, false);
    }

    public View getPackView() {return packView;}

    public void setName(String name) {
        setText(R.id.packName,name);
   };

    private void setText(int id, String text) {
        TextView view = packView.findViewById(id);
        view.setText(text);
    }

}
