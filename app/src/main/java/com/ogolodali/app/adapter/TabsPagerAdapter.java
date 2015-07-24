package com.ogolodali.app.adapter;

import com.ogolodali.app.view.IngredientsFragment;
import com.ogolodali.app.view.SearchFragment;
import com.ogolodali.app.view.TagsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	private static Fragment[] fragments = { new SearchFragment(), new IngredientsFragment(), new TagsFragment()};

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		/*switch (index) {
		case 0:
			return new SearchFragment();
		case 1:
			return new IngredientsFragment();
		case 2:
			return new TagsFragment();
		}*/
		if (index >= 0 && index <= fragments.length) {
			return fragments[index];
		}
		return null;
	}

	@Override
	public int getCount() {
		return fragments.length;
	}

}
