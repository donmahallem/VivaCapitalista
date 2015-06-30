package de.xants.capitalista.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.xants.capitalista.CM;
import de.xants.capitalista.R;
import de.xants.capitalista.rv.ProductionRecyclerAdapter;

public class FragmentProductionOverview extends FragmentToolbar {

    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;

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
        this.mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_content);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        this.mRecyclerView.setAdapter(new ProductionRecyclerAdapter());
        if (this.getActivity() instanceof AppCompatActivity)
            ((AppCompatActivity) this.getActivity()).setSupportActionBar(this.getToolbar());
        this.getToolbar().setNavigationIcon(R.drawable.ic_drawer);
    }

    @Override
    public void onResume() {
        super.onResume();
        CM.getBus().register(this);
    }

    @Override
    public void onPause() {
        CM.getBus().unregister(this);
        super.onPause();
    }
}
