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
import androidx.lifecycle.ViewModelProvider;

import com.internetEnemies.combatCritters.Logic.ItemStackExtractor;
import com.internetEnemies.combatCritters.R;
import com.internetEnemies.combatCritters.databinding.FragmentMarketBuyBinding;
import com.internetEnemies.combatCritters.objects.Card;
import com.internetEnemies.combatCritters.objects.Currency;
import com.internetEnemies.combatCritters.objects.IItem;
import com.internetEnemies.combatCritters.objects.ItemStack;
import com.internetEnemies.combatCritters.objects.Pack;
import com.internetEnemies.combatCritters.objects.Transaction;
import com.internetEnemies.combatCritters.presentation.renderable.CardRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.ItemRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.PackRenderer;
import com.internetEnemies.combatCritters.presentation.renderable.TransactionRenderer;

import java.util.ArrayList;
import java.util.List;

public class MarketBuyFragment extends Fragment {
    private FragmentMarketBuyBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;
    private TextView transactionDetails;
    private Fragment selectedFrag;


    public MarketBuyFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMarketBuyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transactionDetails = view.findViewById(R.id.costText);
        selectedFrag = null;

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        selectedOffersViewModel.getSelectedPosition().observe(this.getViewLifecycleOwner(), __ -> refresh());
    }

    private void refresh() {
//        refreshGridView();
        displayTransactionDetails(selectedOffersViewModel.getTransaction());
        setFrag(selectedOffersViewModel.getTransaction());
    }

    private void displayTransactionDetails(Transaction transaction) {
        if(transaction == null) {
            transactionDetails.setText("");
            return;
        }

        int cost = ((Currency)(transaction.getGivenFirstItem().getItem())).getAmount();

        if(transaction.getReceived().size() > 1) { //It's a bundle!
            String detailsText = getString(R.string.transaction_details, "Bundle", cost);
            transactionDetails.setText(detailsText);
        }
        else { //It's a Card or Pack
            IItem item = transaction.getReceivedFirstItem().getItem();
            ItemNameFetcher fetcher = new ItemNameFetcher();
            item.accept(fetcher);
            String detailsText = getString(R.string.transaction_details, fetcher.getItemName(), cost);
            transactionDetails.setText(detailsText);
        }
    }

    private void setFrag(Transaction transaction) {
        if (transaction == null) {
            if(selectedFrag != null) {
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
            else { //It's a card or pack
                IItem item = transaction.getReceivedFirstItem().getItem();
                SetFragmentVisitor fragmentVisitor = new SetFragmentVisitor(getChildFragmentManager());
                item.accept(fragmentVisitor);
                fragmentVisitor.setFragment(R.id.fragContainer);
            }
        }

        if (selectedFrag != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragContainer, selectedFrag).commit();
        }
    }

    public List<Card> getCards(List<ItemStack<?>> items) {
        List<Card> cards = new ArrayList<>();
        for (ItemStack<?> item : items) {
            if (item.getItem() instanceof Card) {
                cards.add((Card) item.getItem());
            }
        }
        return cards;
    }

    public List<Pack> getPacks(List<ItemStack<?>> items) {
        List<Pack> packs = new ArrayList<>();
        for (ItemStack<?> item : items) {
            if (item.getItem() instanceof Pack) {
                packs.add((Pack) item.getItem());
            }
        }
        return packs;
    }

}