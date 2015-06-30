package de.xants.capitalista.model.otto;

import android.support.annotation.NonNull;

import de.xants.capitalista.model.Fragments;

public class FragmentShowEvent {
    public final Fragments FRAGMENT;

    private FragmentShowEvent(Fragments fragments) {
        this.FRAGMENT = fragments;
    }

    public static FragmentShowEvent create(@NonNull Fragments fragments) {
        if (fragments == null)
            throw new NullPointerException("Argument fragments must not be null");
        return new FragmentShowEvent(fragments);
    }
}
