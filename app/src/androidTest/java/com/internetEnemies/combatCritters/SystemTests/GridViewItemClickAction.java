package com.internetEnemies.combatCritters.SystemTests;

import android.view.View;
import android.widget.GridView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;

public class GridViewItemClickAction {
    public static ViewAction clickOnItemPosition(int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(GridView.class));
            }

            @Override
            public String getDescription() {
                return "Click on item at position: " + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                GridView gridView = (GridView) view;
                if (position < gridView.getAdapter().getCount()) {
                    gridView.performItemClick(
                            gridView.getAdapter().getView(position, null, null),
                            position,
                            gridView.getAdapter().getItemId(position)
                    );
                }
            }
        };
    }
}

