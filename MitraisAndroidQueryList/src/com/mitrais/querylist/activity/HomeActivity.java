package com.mitrais.querylist.activity;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.mitrais.querylist.R;
import com.mitrais.querylist.fragment.ViewPagerContent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class HomeActivity extends FragmentActivity {

	protected AQuery listAq;
	protected AQuery aq;
	private ViewPager viewPager;
	private FragmentStatePagerAdapter adapter; 
	private boolean showArticle; 
	
	String[] toVisit={
	        "http://www.jdepths.com",
	        "http://m.google.com/",
	        "http://www.reddit.com/.compact",
	        "http://www.dribbble.com",
	    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		aq = new AQuery(this);
		setContentView(getContainer());
		viewPager = (ViewPager)findViewById(R.id.pagerArticle);
		work();
	}

	protected int getContainer() {
		return R.layout.image_list_activity;
	}

	public void work() {
		String url = "http://www.google.com/uds/GnewsSearch?q=miss+world&v=2.0&rsz=8";
		aq.progress(R.id.progress).ajax(url, JSONObject.class, renderNews(this));
	}

	private AjaxCallback<JSONObject> renderNews(final Context context) {
		return new AjaxCallback<JSONObject>(){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				if (json == null) return;

				JSONArray ja = json.optJSONObject("responseData").optJSONArray("results");
				if (ja == null) return;

				List<JSONObject> items = new ArrayList<JSONObject>();
				addItems(ja, items);

				listAq = new AQuery(context);
				aq.id(R.id.list).adapter(getAdapter(items)).itemClicked(onItemClicked(context));
			}
			
		};
	}

	protected OnItemClickListener onItemClicked(Context context) {
		return new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {

				aq.id(R.id.article_layout).visible();
				aq.id(R.id.list_layout).gone();
				
				adapter=new FragmentStatePagerAdapter(
			            getSupportFragmentManager()
			            ){
			    
			            @Override
			            public int getCount() {
			                // This makes sure getItem doesn't use a position
			               // that is out of bounds of our array of URLs
			               return toVisit.length;
			            }
			    
			            @Override
			            public Fragment getItem(int position) {
			                // Here is where all the magic of the adapter happens
			                // As you can see, this is really simple.
			                return ViewPagerContent.newInstance(toVisit[position]);
			            }
			        };
			      viewPager.setAdapter(adapter);
			      showArticle = true;
			}
		};
	}

	protected Adapter getAdapter(List<JSONObject> items) {
		return new ArrayAdapter<JSONObject>(this, R.layout.content_item_s, items) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				if (convertView == null) {
					convertView = getLayoutInflater().inflate(R.layout.content_item_s, null);
				}

				JSONObject jo = getItem(position);

				AQuery aq = listAq.recycle(convertView);
				aq.id(R.id.name).text(Html.fromHtml(jo.optString("titleNoFormatting", "No Title")));
				aq.id(R.id.meta).text(Html.fromHtml(jo.optString("publisher", "")));

				String tb = jo.optJSONObject("image").optString("tbUrl");
				aq.id(R.id.tb).progress(R.id.progress).image(tb, true, true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);

				return convertView;

			}
		};
	}
	
//	/***
//	 * Show popup message
//	 * @param message
//	 */
//	private void toastIt(String message) {
//		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//	}

	private void addItems(JSONArray ja, List<JSONObject> items) {
		for (int i = 0; i < ja.length(); i++) {
			JSONObject jo = ja.optJSONObject(i);
			if (jo.has("image")) {
				items.add(jo);
			}
		}
	}
	
//	private void showFragment(int layoutId, Fragment fragment, String tagName) {
//		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
//	
//		
//		fragmentTransaction.replace(layoutId, fragment, tagName);
//		fragmentTransaction.commit();
//	}
	
	@Override
	public void onBackPressed(){
		if(!showArticle) {
			super.onBackPressed();
		}			
		else {
			showArticle = false;
			aq.id(R.id.article_layout).gone();
			aq.id(R.id.list_layout).visible();
		}
	}
}
