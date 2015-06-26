package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class ProductionRecyclerAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        return new ProductionViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 12;
    }
}
