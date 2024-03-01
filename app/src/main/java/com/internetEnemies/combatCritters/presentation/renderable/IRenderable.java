package com.internetEnemies.combatCritters.presentation.renderable;

import android.view.View;
import android.view.ViewGroup;

/**
 * IRenderable.java
 * COMP 3350 A02
 * @Project     Combat Critters
 * @created     2/29/24
 *
 * @PURPOSE:    Used for objects that can be rendered into a view
 */
public interface IRenderable {
    /**
     * get the view from a the renderable component
     * @param view view to reuse
     * @param parent parent to attach to
     * @return adjusted view
     */
    View getView(View view, ViewGroup parent);
}
