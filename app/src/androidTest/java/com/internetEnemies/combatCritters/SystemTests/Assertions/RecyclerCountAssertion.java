package com.internetEnemies.combatCritters.SystemTests.Assertions;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;

import static org.hamcrest.Matchers.is;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class RecyclerCountAssertion implements ViewAssertion {
    private final int expected;
    public RecyclerCountAssertion(int expected) {
        this.expected = expected;
    }
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        RecyclerView recyclerView = (RecyclerView) view;
        assertThat(recyclerView.getAdapter().getItemCount(), is(this.expected));
    }
}
