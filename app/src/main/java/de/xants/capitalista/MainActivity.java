package de.xants.capitalista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import de.xants.capitalista.model.otto.UpgradeRequestEvent;
import de.xants.capitalista.rv.ProductionRecyclerAdapter;

public class MainActivity extends AppCompatActivity {


    GameService mService;
    boolean mBound = false;
    Intent intent;
    private DrawerLayout mDrawerLayout;
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mCoordinatorLayout = (CoordinatorLayout) this.findViewById(R.id.main_content);
        this.mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setAdapter(new ProductionRecyclerAdapter());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
    }

    @Override
    public void onResume() {
        super.onResume();
        intent = new Intent(this, GameService.class);
        startService(intent);
        CM.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopService(intent);
        CM.getBus().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onUpgradeEvent(UpgradeRequestEvent event) {
        Snackbar
                .make(this.mCoordinatorLayout, "" + event.getProductionType().toString(), Snackbar.LENGTH_LONG)
                .show(); // Don’t forget to show!
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
