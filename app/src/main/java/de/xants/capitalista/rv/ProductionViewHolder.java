package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.xants.capitalista.R;
import de.xants.capitalista.model.Production;

public class ProductionViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvIcon;
    private TextView mTvTitle;
    public ProductionViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production, parent, false));
        this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.iv_icon);
        this.mTvTitle = (TextView) this.itemView.findViewById(R.id.tv_title);
    }

    public void setData(Production productionType) {
        if (productionType == null)
            return;
        this.mIvIcon.setImageResource(productionType.getProductionType().DRAWABLE);
        this.mTvTitle.setText(productionType.getProductionType().TITLE);
    }
}
