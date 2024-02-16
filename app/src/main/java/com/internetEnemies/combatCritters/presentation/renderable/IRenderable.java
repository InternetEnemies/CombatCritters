package com.internetEnemies.combatCritters.presentation.renderable;

import android.view.View;
import android.view.ViewGroup;

public interface IRenderable {
    /**
     * get the view from a the renderable component
     * @param view view to reuse
     * @param parent parent to attach to
     * @return adjusted view
     */
    View getView(View view, ViewGroup parent);
}
