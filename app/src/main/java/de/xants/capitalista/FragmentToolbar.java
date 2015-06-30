package de.xants.capitalista;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

public abstract class FragmentToolbar extends android.support.v4.app.Fragment {
    private Toolbar mToolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (this.mToolbar == null)
            throw new NullPointerException(getClass().getCanonicalName() + " must include a Toolbar with id toolbar to work");
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
