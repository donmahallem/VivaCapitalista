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

import de.xants.capitalista.CM;
import de.xants.capitalista.R;
import de.xants.capitalista.model.Upgrade;
import de.xants.capitalista.model.otto.UpgradeBuyEvent;

public class UpgradesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView mIvIcon;
    private TextView mTvTitle, mTvCost;
    private Button mBtnUpgrade;
    private Upgrade mUpgrade;

    public UpgradesViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_upgrades, viewGroup, false));
        this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.iv_icon);
        this.mTvTitle = (TextView) this.itemView.findViewById(R.id.tv_title);
        this.mTvCost = (TextView) this.itemView.findViewById(R.id.tv_cost);
        this.mBtnUpgrade = (Button) this.itemView.findViewById(R.id.btn_upgrade);
        this.mBtnUpgrade.setOnClickListener(this);
    }

    public void setUpgrade(Upgrade upgrade) {
        if (upgrade == null)
            return;
        if (this.mUpgrade != null && this.mUpgrade.equals(upgrade))
            return;
        this.mUpgrade = upgrade;
        this.mTvTitle.setText(upgrade.PRODUCTION_TYPE.TITLE);
        this.mIvIcon.setImageResource(upgrade.PRODUCTION_TYPE.DRAWABLE);
        this.mTvCost.setText("" + upgrade.COST);
    }

    @Override
    public void onClick(View v) {
        if (v == this.mBtnUpgrade) {
            CM.getBus().post(UpgradeBuyEvent.create(this.mUpgrade));
        }
    }
}
