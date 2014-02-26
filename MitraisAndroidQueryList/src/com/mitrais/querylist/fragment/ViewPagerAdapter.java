package com.mitrais.querylist.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	String[] toVisit={
	        "http://www.jdepths.com",
	        "http://www.google.com",
	        "http://www.reddit.com/.compact",
	        "http://www.dribbble.com",
	    };
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return ViewPagerContent.newInstance(toVisit[position]);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return toVisit.length;
	}
}
