package com.internetEnemies.combatCritters.presentation;

import android.view.View;


public class ScaleView {
    public static void scaleView(View view, float scaleFactor) {
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
    }
}
