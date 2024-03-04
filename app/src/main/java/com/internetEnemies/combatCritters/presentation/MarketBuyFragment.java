package com.internetEnemies.combatCritters.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
    private ItemGridFragment<?> gridFrag;
    private FragmentMarketBuyBinding binding;
    private MarketplaceViewModel selectedOffersViewModel;
    private TextView transactionDetails;


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

        if(gridFrag == null) {
            gridFrag = new ItemGridFragment<>(new ArrayList<>());
        }
        getChildFragmentManager().beginTransaction().replace(R.id.buyFragmentGridContainer, gridFrag).commit();

        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity());
        selectedOffersViewModel = viewModelProvider.get(MarketplaceViewModel.class);
        selectedOffersViewModel.getSelectedPosition().observe(this.getViewLifecycleOwner(), __ -> refresh());

    }

    private void refresh() {
        refreshGridView();
        displayTransactionDetails(selectedOffersViewModel.getTransaction());
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

    //Refresh the gridview with the details of the selected item
    private void refreshGridView() {
        Transaction selectedTransaction = selectedOffersViewModel.getTransaction();
        if(selectedTransaction == null) {
            gridFrag.updateItems(new ArrayList<>());
            return;
        }

        List<ItemRenderer<?>> renderers = new ArrayList<>();
        if(selectedTransaction.getReceived().size() > 0) {
            for(ItemStack<?> itemStack : selectedTransaction.getReceived()) {
                if(itemStack.getItem() instanceof Pack) {
                    renderers.add(new PackRenderer((Pack)itemStack.getItem(), this.getContext()));
                }
                else {
                    renderers.add(new CardRenderer((Card)itemStack.getItem(), this.getContext()));
                }
            }
        }
//        gridFrag.updateItems(renderers);
//            if(selectedTransaction.getReceived().size() > 1) { //It's a bundle!
//                for(ItemStack<?> itemStack: selectedTransaction.getReceived()) {
//
//                }
//            }
//            else { //It's a card or pack
//                RenderingVisitor rend = new RenderingVisitor(this.getContext());
//                IItem item = selectedTransaction.getReceivedFirstItem().getItem();
//                item.accept(rend);
//                gridFrag.updateItems(rend.getRenderers());
//            }

    }
}
