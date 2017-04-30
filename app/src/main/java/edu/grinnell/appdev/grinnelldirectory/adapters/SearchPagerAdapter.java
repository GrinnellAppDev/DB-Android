package edu.grinnell.appdev.grinnelldirectory.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewGroupCompat;
import android.view.ViewGroup;
import edu.grinnell.appdev.grinnelldirectory.fragments.AdvancedSearchFragment;
import edu.grinnell.appdev.grinnelldirectory.fragments.SimpleSearchFragment;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchFragmentInterface;

/**
 * Created by nicholasroberson on 4/16/17.
 */

public class SearchPagerAdapter extends FragmentPagerAdapter {

    private SearchFragmentInterface mCurrentFragment;

    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SearchFragmentInterface getCurrentFragment() {
        return mCurrentFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Simple";
            case 1:
                return "Advanced";
        }
        return "Simple";
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new SimpleSearchFragment();
                break;
            case 1:
                fragment = new AdvancedSearchFragment();
                break;
            default:
                fragment = new SimpleSearchFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    /**
     * Called to inform the adapter of which item is current
     *
     * @param container The containing view from which the page will be removed
     * @param position The page position that is being shown
     * @param object represents the page, created by PagerAdapter.instantiateItem
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentFragment = (SearchFragmentInterface) object;
    }
}
