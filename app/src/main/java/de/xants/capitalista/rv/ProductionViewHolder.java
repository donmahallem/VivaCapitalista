/*
 * Copyright 2015 https://github.com/donmahallem/VivaCapitalista
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import de.xants.capitalista.CM;
import de.xants.capitalista.R;
import de.xants.capitalista.model.Production;
import de.xants.capitalista.model.otto.ProductionUpgradeEvent;

public class ProductionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Button mBtnUpgrade;

    private ImageView mIvIcon;
    private TextView mTvTitle;
    private TextView mTvLevel;
    private Production mProduction;
    private TextView mTvCost;

    public ProductionViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production, parent, false));
        this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.iv_icon);
        this.mTvTitle = (TextView) this.itemView.findViewById(R.id.tv_title);
        this.mTvLevel = (TextView) this.itemView.findViewById(R.id.tv_level);
        this.mTvCost = (TextView) this.itemView.findViewById(R.id.tv_cost);
        this.mBtnUpgrade = (Button) this.itemView.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setOnClickListener(this);
    }

    @Subscribe
    public void onProductionUpgrade(ProductionUpgradeEvent productionUpgradeEvent) {
        if (productionUpgradeEvent == null || this.mProduction == null)
            return;
        if (productionUpgradeEvent.PRODUCTION_TYPE == this.mProduction.getProductionType()) {
            this.mProduction.setLevel(productionUpgradeEvent.LEVEL);
            updateData();
        }
    }

    private void updateData() {
        this.mIvIcon.setImageResource(this.mProduction.getProductionType().DRAWABLE);
        this.mTvTitle.setText(this.mProduction.getProductionType().TITLE);
        this.mTvLevel.setText("" + this.mProduction.getLevel());
        this.mTvCost.setText(this.itemView.getResources().getString(R.string.format_currency, this.mProduction.upgradeCost()));
    }

    public void setData(Production production) {
        if (production == null)
            return;
        this.mProduction = production;
        updateData();
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnUpgrade) {
            CM.getBus().post(ProductionUpgradeEvent.create(this.mProduction.getLevel() + 1, this.mProduction.getProductionType()));
        }
    }
}
