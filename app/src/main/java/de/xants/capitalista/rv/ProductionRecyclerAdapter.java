package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.xants.capitalista.CM;
import de.xants.capitalista.model.Production;
import de.xants.capitalista.model.ProductionType;

public class ProductionRecyclerAdapter extends RecyclerView.Adapter<ProductionViewHolder> {
    private List<Production> mProductionList = new ArrayList<Production>();

    public ProductionRecyclerAdapter() {
        this.mProductionList.add(new Production(ProductionType.TYPE1));
        this.mProductionList.add(new Production(ProductionType.TYPE2));
        this.mProductionList.add(new Production(ProductionType.TYPE3));
        this.mProductionList.add(new Production(ProductionType.TYPE4));
        this.mProductionList.add(new Production(ProductionType.TYPE5));
        this.mProductionList.add(new Production(ProductionType.TYPE6));
        this.mProductionList.add(new Production(ProductionType.TYPE7));
        this.mProductionList.add(new Production(ProductionType.TYPE8));
        this.mProductionList.add(new Production(ProductionType.TYPE9));
        this.mProductionList.add(new Production(ProductionType.TYPE1));
        this.mProductionList.add(new Production(ProductionType.TYPE2));
        this.mProductionList.add(new Production(ProductionType.TYPE3));
        this.mProductionList.add(new Production(ProductionType.TYPE4));
        this.mProductionList.add(new Production(ProductionType.TYPE5));
        this.mProductionList.add(new Production(ProductionType.TYPE6));
        this.mProductionList.add(new Production(ProductionType.TYPE7));
        this.mProductionList.add(new Production(ProductionType.TYPE8));
        this.mProductionList.add(new Production(ProductionType.TYPE9));
    }

    @Override
    public ProductionViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        return new ProductionViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(ProductionViewHolder viewHolder, int position) {
        viewHolder.setData(this.mProductionList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mProductionList.size();
    }

    @Override
    public void onViewAttachedToWindow(ProductionViewHolder holder) {
        CM.getBus().register(holder);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ProductionViewHolder holder) {
        CM.getBus().unregister(holder);
        super.onViewDetachedFromWindow(holder);
    }
}
