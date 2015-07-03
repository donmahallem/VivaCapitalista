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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.xants.capitalista.R;

public class FragmentUpgradesOverview extends FragmentToolbar {
    private CoordinatorLayout mCoordinatorLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
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
        this.mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        this.mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        setupViewPager();
        this.mTabLayout.setupWithViewPager(this.mViewPager);
        if (this.getActivity() instanceof AppCompatActivity)
            ((AppCompatActivity) this.getActivity()).setSupportActionBar(this.getToolbar());
        this.getToolbar().setNavigationIcon(R.drawable.ic_menu_white_36dp);
        this.getActivity().setTitle(R.string.upgrades);
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FragmentUpgradesAvailable(), "Category 1");
        adapter.addFragment(new FragmentUpgradesActivated(), "Category 2");
        this.mViewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
