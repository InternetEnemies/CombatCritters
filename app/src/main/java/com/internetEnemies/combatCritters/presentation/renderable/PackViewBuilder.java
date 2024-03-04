package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.CardSlot;
import com.internetEnemies.combatCritters.objects.CardType;
import com.internetEnemies.combatCritters.objects.ICardBuilder;
import com.internetEnemies.combatCritters.objects.IPackBuilder;
import com.internetEnemies.combatCritters.objects.Pack;

import java.util.List;

public class PackViewBuilder  {
    private final View packView;
    private final Context context;

    public PackViewBuilder(Context context, ViewGroup parent){
        this.context = context;
        this.packView = LayoutInflater.from(this.context).inflate(R.layout.pack, parent, false);
    }


    /**
     * get the UI View for the card built by this builder
     * @return View for the card
     */
    public View getPackView() {return packView;}


    public void setName(String name) {
        setText(R.id.packName,name);
   };


    /**
     * set the text of a TextView given the id and a text to set it to
     * @param id id of TextView
     * @param text text to set to
     */
    private void setText(int id, String text) {
        TextView view = packView.findViewById(id);
        view.setText(text);
    }

}
