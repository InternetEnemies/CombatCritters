package com.internetEnemies.combatCritters.SystemTests.Assertions;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class RecyclerCountMinimumAssertion implements ViewAssertion{
    private final int minimum;
    public RecyclerCountMinimumAssertion(int minimum) {
        this.minimum = minimum;
    }
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        RecyclerView recyclerView = (RecyclerView) view;
        assertThat(recyclerView.getAdapter().getItemCount(), greaterThanOrEqualTo(this.minimum));
    }
}
