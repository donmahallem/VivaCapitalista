package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.xants.capitalista.CM;
import de.xants.capitalista.R;
import de.xants.capitalista.model.Production;
import de.xants.capitalista.model.UpgradeEvent;

public class ProductionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Button mBtnUpgrade;

    private ImageView mIvIcon;
    private TextView mTvTitle;

    private Production mProduction;
    public ProductionViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production, parent, false));
        this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.iv_icon);
        this.mTvTitle = (TextView) this.itemView.findViewById(R.id.tv_title);
        this.mBtnUpgrade = (Button) this.itemView.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setOnClickListener(this);
    }

    public void setData(Production production) {
        if (production == null)
            return;
        this.mProduction = production;
        this.mIvIcon.setImageResource(production.getProductionType().DRAWABLE);
        this.mTvTitle.setText(production.getProductionType().TITLE);
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnUpgrade) {
            CM.getBus().post(new UpgradeEvent(this.mProduction.getProductionType()));
        }
    }
}
