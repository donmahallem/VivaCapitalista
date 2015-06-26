package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import de.xants.capitalista.R;

public class ProductionViewHolder extends RecyclerView.ViewHolder {
    public ProductionViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production, parent, false));
    }
}
