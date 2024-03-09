package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.IPackBuilder;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public class PackViewBuilder {
    Context context;
    View packView;
    public PackViewBuilder(Context context, View packView, String name) {
        this.context = context;
        this.packView = packView;
        TextView packText = packView.findViewById(R.id.packName);
        packText.setText(name);
    }

    public View getPackView() {
        return packView;
    }
}
