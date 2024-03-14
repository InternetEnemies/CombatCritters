package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.internetEnemies.combatCritters.Logic.IItemStackListExtractor;
import com.internetEnemies.combatCritters.Logic.IMarketHandler;
import com.internetEnemies.combatCritters.Logic.ItemStackListExtractor;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentMarketBuyBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

import java.util.ArrayList;

/**
 * MarketBuyFragment.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created      06-March-2024
 *
 * @PURPOSE:     Part of MarketplaceActivity. This fragment handles transaction purchases.
 */
public class MarketBuyFragment extends Fragment {
    private MarketplaceViewModel selectedOffersViewModel;
    private IBuyButtonClickListener buttonClickListener;
    private Fragment selectedFrag;
    private IMarketHandler marketHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.internetEnemies.combatCritters.databinding.FragmentMarketBuyBinding binding = FragmentMarketBuyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedFrag = null;
        marketHandler = new MarketHandler();

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        selectedOffersViewModel.getSelectedPosition().observe(this.getViewLifecycleOwner(), __ -> refresh());

        setupBuyButton();
    }

    public void setBuyButtonClickListener(IBuyButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    /**
     * Refreshes the fragment with the most recently selected item.
     */
    private void refresh() {
        displayPrice(selectedOffersViewModel.getTransaction());
        setFrag(selectedOffersViewModel.getTransaction());
    }

    private void setupBuyButton() {
        Button buyButton = getView().findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOffersViewModel.getTransaction() != null) {
                    buySelectedItem();
                }
                else {
                    Toast.makeText(getActivity(), "No offer selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Callback to parent.
     */
    private void buySelectedItem() {
        if (buttonClickListener != null) {
            buttonClickListener.onBuyButtonClicked();
        }
    }

    /**
     * Displays the cost of the currently selected transaction.
     *
     * @param transaction transaction cost to display.
     */
    private void displayPrice(MarketTransaction transaction) {
        ViewGroup currencyContainer = getView().findViewById(R.id.currency_container);
        TextView costTextView = getView().findViewById(R.id.text_view);

        if (transaction == null) {
            costTextView.setText("");
            currencyContainer.removeAllViews();
            return;
        }

        costTextView.setText(getString(R.string.transaction_cost));


        CurrencyRenderer currencyRenderer = new CurrencyRenderer(transaction.getPrice(), getContext());
        View currencyView = currencyRenderer.getView(null, currencyContainer);
        currencyContainer.removeAllViews();
        currencyContainer.addView(currencyView);
    }


    /**
     * Switches the fragment displayed in fragContainer based on the type of transaction currently
     * selected. If no transaction is selected set the fragContainer to empty.
     *
     * @param transaction transaction selected.
     */
    private void setFrag(MarketTransaction transaction) {
        if (transaction == null) {
            if (selectedFrag != null) {
                getChildFragmentManager().beginTransaction().remove(selectedFrag).commit();
                selectedFrag = null;
            }
        }
        else {
            if (transaction.getReceived().size() > 1) { //It's a bundle!
                selectedFrag = new BundleFragment();
                Bundle args = new Bundle();
                IItemStackListExtractor extractor = new ItemStackListExtractor(transaction.getReceived());

                args.putSerializable("cards", new ArrayList<>(extractor.getCards()));
                args.putSerializable("packs", new ArrayList<>(extractor.getPacks()));
                selectedFrag.setArguments(args);
            }
            else if (transaction.getReceivedFirstItem().getItem() instanceof Card) {
                selectedFrag = new CardFragment();
                Bundle args = new Bundle();

                args.putSerializable("card", (Card) transaction.getReceivedFirstItem().getItem());
                selectedFrag.setArguments(args);
            }
            else {
                selectedFrag = new PackFragment();
                Bundle args = new Bundle();

                args.putSerializable("pack", (Pack) transaction.getReceivedFirstItem().getItem());
                selectedFrag.setArguments(args);
            }
        }

        if (selectedFrag != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragContainer, selectedFrag).commit();
        }
    }

    /**
     * @PURPOSE:     Callback for handling buy button clicks.
     */
    public interface IBuyButtonClickListener {
        /**
         * Perform some action when buy button is clicked.
         */
        void onBuyButtonClicked();
    }
}