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

package de.xants.capitalista.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.xants.capitalista.R;
import de.xants.capitalista.model.ProductionType;

public class FragmentUpgradesActivated extends Fragment {
    private RecyclerView mRecyclerView;
    private UpgradesActivatedRecyclerAdapter mUpgradesActivatedRecyclerAdapter = new UpgradesActivatedRecyclerAdapter();

    public static Fragment createInstance() {
        return new FragmentUpgradesActivated();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scrollview_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.mRecyclerView.setAdapter(this.mUpgradesActivatedRecyclerAdapter);
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;

        public TitleViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_title, parent, false));
            this.mTvTitle = (TextView) this.itemView;
        }

        public void setTitle(@StringRes @Nullable int title) {
            this.mTvTitle.setText(title);
        }

        public void setTitle(@StringRes @Nullable String title) {
            this.mTvTitle.setText(title);
        }
    }

    static class ActivatedUpgradeViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvIcon;
        private TextView mTvTitle;
        private TextView mTvMultiplier;

        public ActivatedUpgradeViewHolder(ViewGroup parent) {
            super(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.listitem_activated_upgrade, parent, false));
            this.mTvTitle = (TextView) this.itemView.findViewById(R.id.tv_title);
            this.mIvIcon = (ImageView) this.itemView.findViewById(R.id.iv_icon);
            this.mTvMultiplier = (TextView) this.itemView.findViewById(R.id.tv_multiplier);
        }

        public void setProductionType(@NonNull ProductionType productionType) {
            this.mTvTitle.setText(productionType.TITLE);
            this.mIvIcon.setImageResource(productionType.DRAWABLE);
            this.mTvMultiplier.setText(
                    this.itemView.getContext()
                            .getResources().getString(R.string.format_multiplier, 29d));
        }
    }

    static class UpgradesActivatedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final static int TYPE_TITLE_PRODUCTION = 0,
                TYPE_TITLE_OTHER = 1,
                TYPE_DETAIL = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_DETAIL:
                    return new ActivatedUpgradeViewHolder(parent);
                case TYPE_TITLE_PRODUCTION:
                case TYPE_TITLE_OTHER:
                    return new TitleViewHolder(parent);
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TitleViewHolder) {
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                switch (getItemViewType(position)) {
                    case TYPE_TITLE_OTHER:
                        titleViewHolder.setTitle(R.string.other);
                        break;
                    case TYPE_TITLE_PRODUCTION:
                        titleViewHolder.setTitle(R.string.production);
                        break;
                }
            } else if (holder instanceof ActivatedUpgradeViewHolder) {
                ActivatedUpgradeViewHolder activatedUpgradeViewHolder = (ActivatedUpgradeViewHolder) holder;
                activatedUpgradeViewHolder.setProductionType(ProductionType.values()[(position - 1) % 10]);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return TYPE_TITLE_PRODUCTION;
            else if (position == 11)
                return TYPE_TITLE_OTHER;
            return TYPE_DETAIL;
        }

        @Override
        public int getItemCount() {
            return ProductionType.values().length * 2;
        }
    }
}
