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
import android.widget.ImageView;

import com.squareup.otto.Subscribe;

import java.util.Random;

import de.xants.capitalista.fragments.FragmentProductionOverview;
import de.xants.capitalista.fragments.FragmentUpgradesOverview;
import de.xants.capitalista.model.Fragments;
import de.xants.capitalista.model.otto.FragmentShowEvent;

public class MainActivity extends AppCompatActivity {


    private final static String[] NAV_BAR_IMAGE_URLS = new String[]{"https://raw.githubusercontent.com/donmahallem/VivaCapitalista/master/art/nav_bar/bg2.jpg"};
    private final static Random RANDOM = new Random();
    GameService mService;
    boolean mBound = false;
    Intent intent;
    private DrawerLayout mDrawerLayout;
    private ImageView mIvNavDrawerHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);*/
        this.mIvNavDrawerHeader = (ImageView) findViewById(R.id.iv_nav_drawer_head);


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
        CM.getPicasso().load(NAV_BAR_IMAGE_URLS[RANDOM.nextInt(NAV_BAR_IMAGE_URLS.length)])
                .placeholder(R.drawable.ic_menu_white_36dp)
                .error(R.drawable.ic_dvr_black_48dp)
                .into(this.mIvNavDrawerHeader);
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
