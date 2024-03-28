package com.internetEnemies.combatCritters.presentation.renderable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.objects.battles.Opponent;

import java.util.ArrayList;
import java.util.List;

/**
 * OpponentRenderer.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     25/March/24
 *
 * @PURPOSE:    provide a View object for an opponent
 */
public class OpponentRenderer extends ItemRenderer<Opponent>{
    public OpponentRenderer(Opponent opponent, Context context) {super(opponent, context);}

    @Override
    public View getView(View view, ViewGroup parent) {
        View opponentView = LayoutInflater.from(getContext()).inflate(R.layout.opponent, parent, false);

        TextView descriptionText = opponentView.findViewById(R.id.opponentDescription);
        descriptionText.setText(getItem().getDescription());

        TextView nameText = opponentView.findViewById(R.id.opponentName);
        nameText.setText(getItem().getName());

        ImageView opponentImage = opponentView.findViewById(R.id.opponentImage);
        int resId = getContext().getResources().getIdentifier(getItem().getImage(),"drawable",getContext().getPackageName());
        opponentImage.setImageResource(resId);

        return opponentView;
    }

    /**
     * Helper function for getting opponents from opponent renderers
     * @param opponents list of opponents
     * @param context context for the view
     * @return List of OpponentRenderers
     */
    public static List<ItemRenderer<Opponent>> getRenderers(List<Opponent> opponents , Context context) {
        List<ItemRenderer<Opponent>> renderers = new ArrayList<>();
        for( Opponent opponent : opponents ){
            renderers.add(new OpponentRenderer(opponent, context));
        }
        return renderers;
    }
}
