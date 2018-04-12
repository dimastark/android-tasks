package com.dimastark.superapp.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dimastark.superapp.R;

class FragmentHistoryManager {
    private FragmentManager fragmentManager;

    FragmentHistoryManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    void replace(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    void push(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    void pop(Fragment fragment) {
        fragmentManager.popBackStackImmediate();
        replace(fragment);
    }
}
