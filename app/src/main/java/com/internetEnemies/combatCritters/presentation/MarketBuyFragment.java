package com.internetEnemies.combatCritters.presentation;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.internetEnemies.combatCritters.Logic.ItemStackExtractor;
import com.internetEnemies.combatCritters.Logic.MarketHandler;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentMarketBuyBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.MarketTransaction;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.presentation.renderable.CurrencyRenderer;

import java.util.ArrayList;
import java.util.List;

public class MarketBuyFragment extends Fragment {
    private FragmentMarketBuyBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;
    private TextView transactionDetails;
//    private LinearLayout currencyContainer;
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

//        transactionDetails = view.findViewById(R.id.costText);
//        currencyContainer = view.findViewById(R.id.currency_container);
        selectedFrag = null;

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        selectedOffersViewModel.getSelectedPosition().observe(this.getViewLifecycleOwner(), __ -> refresh());
    }

    private void refresh() {
//        displayTransactionDetails(selectedOffersViewModel.getTransaction());
        displayCost(selectedOffersViewModel.getTransaction());
        setFrag(selectedOffersViewModel.getTransaction());
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
    private void displayTransactionDetails(MarketTransaction transaction) {
        if (transaction == null) {
            transactionDetails.setText("");
            return;
        }

        int cost = transaction.getPrice().getAmount();

        if (transaction.getReceived().size() > 1) { //It's a bundle!
            String detailsText = getString(R.string.transaction_details, "Bundle", cost);
            transactionDetails.setText(detailsText);
        } else { //It's a Card or Pack
            IItem item = transaction.getReceivedFirstItem().getItem();
            ItemNameFetcher fetcher = new ItemNameFetcher();
            item.accept(fetcher);
            String detailsText = getString(R.string.transaction_details, fetcher.getItemName(), cost);
            transactionDetails.setText(detailsText);
        }
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