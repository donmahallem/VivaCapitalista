package de.xants.capitalista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import de.xants.capitalista.model.Fragments;
import de.xants.capitalista.model.otto.FragmentShowEvent;

public class MainActivity extends AppCompatActivity {


    GameService mService;
    boolean mBound = false;
    Intent intent;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);*/

        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        if (this.getSupportFragmentManager().findFragmentById(R.id.fragment_content) == null)
            onShowFragment(FragmentShowEvent.create(Fragments.MAIN));
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
    public void onShowFragment(FragmentShowEvent fragmentShowEvent) {
        FragmentTransaction fragmentTransaction;
        switch (fragmentShowEvent.FRAGMENT) {
            case UPGRADES:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_content, FragmentUpgradesOverview.createInstance(), "upgradesOverview");
                fragmentTransaction.commit();
                break;
            case MAIN:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_content, FragmentProductionOverview.createInstance(), "productionOverview");
                fragmentTransaction.commit();
                break;
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_upgrades:
                                CM.getBus().post(FragmentShowEvent.create(Fragments.UPGRADES));
                                break;
                            case R.id.menu_production:
                                CM.getBus().post(FragmentShowEvent.create(Fragments.MAIN));
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
