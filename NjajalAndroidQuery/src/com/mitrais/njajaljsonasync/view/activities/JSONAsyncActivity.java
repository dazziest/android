package com.mitrais.njajaljsonasync.view.activities;

import java.util.ArrayList;
import java.util.List;

import com.androidquery.AQuery;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.mitrais.njajaljsonasync.R;
import com.mitrais.njajaljsonasync.handler.ErrorReporter;
import com.mitrais.njajaljsonasync.model.ActivityItem;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class JSONAsyncActivity extends Activity {
	private AQuery aq;
	private boolean debug;
	private List<ActivityItem> listItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empty_list);
		
		if (debug) {
			AQUtility.setDebug(true);
			AQUtility.setCacheDir(null);
			BitmapAjaxCallback.setPixelLimit(600 * 600);
			BitmapAjaxCallback.setCacheLimit(200);
			BitmapAjaxCallback.setIconCacheLimit(100);
			BitmapAjaxCallback.setMaxPixelLimit(10000000);
			debug = false;
			ErrorReporter.installReporter(getApplicationContext());
		}
		
		aq = new AQuery(this);
		
		aq.id(R.id.list_example).adapter(getListAdapter()).itemClicked(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ActivityItem activityItem = listItem.get(position);
				if (activityItem.isLink()) {
					toastIt("isLink: "+ activityItem.getType());
				}else {
					toastIt("not link: "+ activityItem.getType());
				}
			}
		});
	}

	private Adapter getListAdapter() {
		listItem = new ArrayList<ActivityItem>();
		
		int listIds[] = getResList();
		String[] names = getResources().getStringArray(listIds[0]);
		String[] values = getResources().getStringArray(listIds[1]);
		
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			String value = values[i];
			if (value.startsWith("http")) {
				listItem.add(new ActivityItem(null, name, value, null));
			}else {
				String[] vs = value.split(":");
				String meta = null;
				if(vs.length > 2){
					meta = vs[2];
				}
				listItem.add(makeActivity(vs[0], name, vs[1], meta));
			}
		}
		
		ArrayAdapter<ActivityItem> adapterResult = new ArrayAdapter<ActivityItem>(this, R.layout.list_item, listItem){
			@Override
			public View getView(int position, View convertView, ViewGroup parent){
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				}
				ActivityItem activityItem = (ActivityItem) getItem(position);
				AQuery aq = new AQuery(convertView);
				String text = activityItem.getName();
				String meta = activityItem.getMeta();
				
				if (meta !=null) {
					text += "   <small><small><font color=\"red\">" + meta + "</font></small></small>";
				}				
				Spanned span = Html.fromHtml(text);
				aq.id(R.id.name).text(span);
				return convertView;
			}
		};
		
		return adapterResult;
	}

	private ActivityItem makeActivity(String string, String name,
			String string2, String meta) {
		// TODO Auto-generated method stub
		return null;
	}

	private int[] getResList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jsonasync, menu);
		return true;
	}
	/***
	 * Show popup message
	 * @param message
	 */
	private void toastIt(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}
}
