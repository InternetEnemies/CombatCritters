package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.internetEnemies.combatCritters.Logic.ItemStackExtractor;
import com.internetEnemies.combatCritters.Logic.TransactionHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.data.CardInventoryStub;
import com.internetEnemies.combatCritters.data.CurrencyInventoryStub;
import com.internetEnemies.combatCritters.data.ICardInventory;
import com.internetEnemies.combatCritters.data.ICurrencyInventory;
import com.internetEnemies.combatCritters.data.IPackInventory;
import com.internetEnemies.combatCritters.data.PackInventoryStub;
import com.internetEnemies.combatCritters.databinding.FragmentMarketBuyBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

import java.util.ArrayList;

public class MarketBuyFragment extends Fragment {
    private FragmentMarketBuyBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;
    private IBuyButtonClickListener buttonClickListener;
    private Fragment selectedFrag;
    private CardFragment cardFragment;
    private PackFragment packFragment;
    private BundleFragment bundleFragment;


    public MarketBuyFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cardFragment = new CardFragment();
        packFragment = new PackFragment();
        bundleFragment = new BundleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMarketBuyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        selectedFrag = null;

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        selectedOffersViewModel.getSelectedPosition().observe(this.getViewLifecycleOwner(), __ -> refresh());

        setupBuyButton();
    }

    public void setBuyButtonClickListener(IBuyButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    private void refresh() {
        displayCost(selectedOffersViewModel.getTransaction());
        setFrag(selectedOffersViewModel.getTransaction());
    }

    private void setupBuyButton() {
        Button buyButton = getView().findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedOffersViewModel.getTransaction() != null) {
                    ICardInventory cardInventory = new CardInventoryStub();
                    IPackInventory packInventory = new PackInventoryStub();
                    ICurrencyInventory currencyInventory = new CurrencyInventoryStub();

                    TransactionHandler tHandler = new TransactionHandler(cardInventory, packInventory, currencyInventory);
                    tHandler.performTransaction(selectedOffersViewModel.getTransaction());
                    buySelectedItem();
                }
            }
        });
    }

    private void buySelectedItem() {
        if (buttonClickListener != null) {
            buttonClickListener.onBuyButtonClicked();
        }
    }

    private void displayCost(MarketTransaction transaction) {
        ViewGroup currencyContainer = getView().findViewById(R.id.currency_container);

        if(transaction == null) {
            currencyContainer.removeAllViews();
            return;
        }

        Currency cost = transaction.getPrice();
        CurrencyRenderer currencyRenderer = new CurrencyRenderer(cost, getContext());
        View currencyView = currencyRenderer.getView(null, currencyContainer);
        currencyContainer.removeAllViews();
        currencyContainer.addView(currencyView);
    }

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
                ItemStackExtractor extractor = new ItemStackExtractor(transaction.getReceived());

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
}





//    private void setFrag(MarketTransaction transaction) {
//
//
//        if (transaction == null ) {
//            if(selectedFrag != null) {
//                getChildFragmentManager().beginTransaction().remove(selectedFrag).commit();
//                selectedFrag = null;
//            }
//        }
//        else {
//            if(transaction.getReceived().size() > 1) {
//                if(selectedFrag == bundleFragment) {
//                    selectedFrag = bundleFragment;
//                    Bundle args = new Bundle();
//                    ItemStackExtractor extractor = new ItemStackExtractor(transaction.getReceived());
//                    args.putSerializable("cards", new ArrayList<>(extractor.getCards()));
//                    args.putSerializable("packs", new ArrayList<>(extractor.getPacks()));
//                    selectedFrag.setArguments(args);
//                }
//                else {
//                    getChildFragmentManager().beginTransaction().remove(selectedFrag).commit();
//                    selectedFrag = null;
//                }
//            }
//            else if(transaction.getReceivedFirstItem().getItem() instanceof Pack) {
//                selectedFrag = packFragment;
//                Bundle args = new Bundle();
//                args.putSerializable("pack", (Pack)transaction.getReceivedFirstItem().getItem());
//                selectedFrag.setArguments(args);
//            }
//            else {
//                selectedFrag = cardFragment;
//                Bundle args = new Bundle();
//                args.putSerializable("card", (Card)transaction.getReceivedFirstItem().getItem());
//                selectedFrag.setArguments(args);
//            }
//        }
////        else {
////            if (transaction.getReceived().size() > 1) { //It's a bundle!
////                selectedFrag = bundleFragment;
////                Bundle args = new Bundle();
////                ItemStackExtractor extractor = new ItemStackExtractor(transaction.getReceived());
////                args.putSerializable("cards", new ArrayList<>(extractor.getCards()));
////                args.putSerializable("packs", new ArrayList<>(extractor.getPacks()));
////                selectedFrag.setArguments(args);
////            }
////            else { //It's a card or pack
////                IItem item = transaction.getReceivedFirstItem().getItem();
////                SetFragmentVisitor fragmentVisitor = new SetFragmentVisitor(getChildFragmentManager());
////                item.accept(fragmentVisitor);
////                fragmentVisitor.setFragment(R.id.fragContainer);
////            }
////        }
//
//        if (selectedFrag != null) {
//            getChildFragmentManager().beginTransaction().replace(R.id.fragContainer, selectedFrag).commit();
//        }
//    }


//        else {
//            if (transaction.getReceived().size() > 1) { //It's a bundle!
//                selectedFrag = new BundleFragment();
//                Bundle args = new Bundle();
//                ItemStackExtractor extractor = new ItemStackExtractor(transaction.getReceived());
//
//                args.putSerializable("cards", new ArrayList<>(extractor.getCards()));
//                args.putSerializable("packs", new ArrayList<>(extractor.getPacks()));
//                selectedFrag.setArguments(args);
//            }
//            else if (transaction.getReceivedFirstItem().getItem() instanceof Card) {
//                selectedFrag = new CardFragment();
//                Bundle args = new Bundle();
//
//                args.putSerializable("card", (Card) transaction.getReceivedFirstItem().getItem());
//                selectedFrag.setArguments(args);
//            }
//            else {
//                selectedFrag = new PackFragment();
//                Bundle args = new Bundle();
//
//                args.putSerializable("pack", (Pack) transaction.getReceivedFirstItem().getItem());
//                selectedFrag.setArguments(args);
//            }
//        }

//        else {
//            if (transaction.getReceived().size() > 1) { //It's a bundle!
//                selectedFrag = bundleFragment;
//                Bundle args = new Bundle();
//                ItemStackExtractor extractor = new ItemStackExtractor(transaction.getReceived());
//                args.putSerializable("cards", new ArrayList<>(extractor.getCards()));
//                args.putSerializable("packs", new ArrayList<>(extractor.getPacks()));
//                selectedFrag.setArguments(args);
//            }
//            else { //It's a card or pack
//                IItem item = transaction.getReceivedFirstItem().getItem();
//                SetFragmentVisitor fragmentVisitor = new SetFragmentVisitor(getChildFragmentManager());
//                item.accept(fragmentVisitor);
//                fragmentVisitor.setFragment(R.id.fragContainer);
//                selectedFrag = fragmentVisitor.getSelectedFragment();
//            }
//        }