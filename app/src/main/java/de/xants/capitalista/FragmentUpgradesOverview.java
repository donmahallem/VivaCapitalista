package de.xants.capitalista;

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

public class FragmentUpgradesOverview extends FragmentToolbar {
    private CoordinatorLayout mCoordinatorLayout;
    private RecyclerView mRecyclerView;

    public static Fragment createInstance() {
        return new FragmentUpgradesOverview();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upgrades_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_content);
        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //this.mRecyclerView.setAdapter(new ProductionRecyclerAdapter());
        if (this.getActivity() instanceof AppCompatActivity)
            ((AppCompatActivity) this.getActivity()).setSupportActionBar(this.getToolbar());
        this.getToolbar().setNavigationIcon(R.drawable.ic_drawer);
    }
}
