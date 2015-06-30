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

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import de.xants.capitalista.CM;
import de.xants.capitalista.model.Upgrade;
import de.xants.capitalista.model.otto.UpgradeBuyEvent;
import timber.log.Timber;

public class UpgradesRecyclerAdapter extends RecyclerView.Adapter<UpgradesViewHolder> {
    private SortedList.Callback<Upgrade> UpgradeListCallback = new SortedList.Callback<Upgrade>() {
        @Override
        public int compare(Upgrade o1, Upgrade o2) {
            return Double.compare(o1.COST, o2.COST);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Upgrade oldItem, Upgrade newItem) {
            if (oldItem == null || newItem == null)
                return false;
            return (oldItem.COST == newItem.COST && oldItem.PRODUCTION_TYPE == newItem.PRODUCTION_TYPE);
        }

        @Override
        public boolean areItemsTheSame(Upgrade item1, Upgrade item2) {
            if (item1 == null || item2 == null)
                return false;
            if (!item1.getClass().equals(item2.getClass()))
                return false;
            return item1.equals(item2);
        }
    };
    private SortedList<Upgrade> mUpgradeList
            = new SortedList<Upgrade>(Upgrade.class, UpgradeListCallback);

    public UpgradesRecyclerAdapter() {
        for (int i = 0; i < 100; i++) {
            this.mUpgradeList.add(Upgrade.create(i));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        CM.getBus().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        CM.getBus().unregister(this);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Subscribe
    public void onUpgradeBought(UpgradeBuyEvent upgradeBuyEvent) {
        Timber.d("Recieved upgrade event");
        this.mUpgradeList.remove(upgradeBuyEvent.UPGRADE);
    }

    @Override
    public UpgradesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UpgradesViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(UpgradesViewHolder holder, int position) {
        if (holder == null)
            return;
        holder.setUpgrade(this.mUpgradeList.get(position));
    }


    @Override
    public int getItemCount() {
        return this.mUpgradeList.size();
    }
}
