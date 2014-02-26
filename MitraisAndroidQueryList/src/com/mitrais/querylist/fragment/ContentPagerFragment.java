package com.mitrais.querylist.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.mitrais.querylist.R;

public class ContentPagerFragment extends Fragment {
	private AQuery aq;
	ViewPager viewPager;
	FragmentStatePagerAdapter adapter; 
	
	String[] toVisit={
	        "http://www.jdepths.com",
	        "http://www.google.com",
	        "http://www.reddit.com/.compact",
	        "http://www.dribbble.com",
	    };
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.viewpager_fragment_screen, container, false);
//		
//		aq = new AQuery(getActivity(), view);
//		
//		viewPager = (ViewPager) aq.id(R.id.pagerArticle).getView();
//		
//		adapter=new FragmentStatePagerAdapter(getFragmentManager()
//	            ){
//	    
//	            @Override
//	            public int getCount() {
//	                // This makes sure getItem doesn't use a position
//	               // that is out of bounds of our array of URLs
//	               return toVisit.length;
//	            }
//	    
//	            @Override
//	            public Fragment getItem(int position) {
//	                // Here is where all the magic of the adapter happens
//	                // As you can see, this is really simple.
//	                return ViewPagerContent.newInstance(toVisit[position]);
//	            }
//	        };
//		
//		viewPager.setAdapter(adapter);
//		viewPager.setCurrentItem(0);
//		//viewPager = (ViewPager) view.findViewById(R.id.pagerArticle);
		return null;		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//aq.id(R.id.pagerArticle).adapter(new ViewPagerAdapter(getFragmentManager()));
	}
	
	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState){
		
	}
	
	@Override
	public void onConfigurationChanged (Configuration newConfig){
		
	}
}
