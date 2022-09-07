package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdaptor extends FragmentPagerAdapter {
    public ViewPagerAdaptor(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new SignInFragment();
        } else {
            return new SignUpFragment();
        }

    }
    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {

            return "Login";
        } else {
            return "SignUp";
        }
    }
}
