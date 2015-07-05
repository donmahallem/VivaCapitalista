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
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.xants.capitalista.CM;
import de.xants.capitalista.R;
import de.xants.capitalista.model.Multiplier;
import de.xants.capitalista.rv.ProductionRecyclerAdapter;
import timber.log.Timber;

public class FragmentProductionOverview extends FragmentToolbar implements View.OnClickListener {

    private final static String KEY_MULTIPLIER = "productionMultiplier";
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mFloatingActionButton;
    private Multiplier mProductionMultiplier = Multiplier.M_1;

    public static Fragment createInstance() {
        return new FragmentProductionOverview();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_production_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_MULTIPLIER)) {
            this.mProductionMultiplier = (Multiplier) savedInstanceState.getSerializable(KEY_MULTIPLIER);
        }
        this.mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        this.mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_content);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.mRecyclerView.setAdapter(new ProductionRecyclerAdapter());
        if (this.getActivity() instanceof AppCompatActivity)
            ((AppCompatActivity) this.getActivity()).setSupportActionBar(this.getToolbar());
        this.getToolbar().setNavigationIcon(R.drawable.ic_menu_white_36dp);
        this.getActivity().setTitle(R.string.production);
        this.mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateProductionMultiplierButton();
        CM.getBus().register(this);
    }

    private void updateProductionMultiplierButton() {
        switch (this.mProductionMultiplier) {
            case M_1:
                this.mFloatingActionButton.setImageResource(R.drawable.ic_production_multiplier_1x_24dp);
                break;
            case M_10:
                this.mFloatingActionButton.setImageResource(R.drawable.ic_production_multiplier_10x_24dp);
                break;
            case M_100:
                this.mFloatingActionButton.setImageResource(R.drawable.ic_production_multiplier_100x_24dp);
                break;
            case M_MAX:
                this.mFloatingActionButton.setImageResource(R.drawable.ic_production_multiplier_max_24dp);
                break;
        }
    }

    private void onProductionMultiplierUpgrade() {
        if (this.mProductionMultiplier == Multiplier.M_1)
            this.mProductionMultiplier = Multiplier.M_10;
        else if (this.mProductionMultiplier == Multiplier.M_10)
            this.mProductionMultiplier = Multiplier.M_100;
        else if (this.mProductionMultiplier == Multiplier.M_100)
            this.mProductionMultiplier = Multiplier.M_MAX;
        else if (this.mProductionMultiplier == Multiplier.M_MAX)
            this.mProductionMultiplier = Multiplier.M_1;
        updateProductionMultiplierButton();
    }

    @Override
    public void onPause() {
        CM.getBus().unregister(this);
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        if (v == this.mFloatingActionButton) {
            Timber.d("OnClick - FloatingActionButton");
            onProductionMultiplierUpgrade();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(KEY_MULTIPLIER, this.mProductionMultiplier);
    }

}
