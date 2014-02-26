package com.nu.njajalmedia.views.fragments;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.nu.njajalmedia.R;
import com.nu.njajalmedia.models.Article;
import com.nu.njajalmedia.models.CustomMenuItem;
import com.nu.njajalmedia.views.adapters.CustomArrayAdapter;
//import com.nu.njajalmedia.views.adapters.LazyAdapter;

public class MainContentFragment extends BaseContentFragment {

	private AQuery aq;
	private ListView list;
//	private LazyAdapter adapter;
	private String currentImgUrl;
	private String currentTitle;
	private String currentDes;
	private int indexId;
	private List<CustomMenuItem> menuItem;
	private ArrayList<Article> listData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		setActionBarTitle("Main Content");
		View v = inflater.inflate(R.layout.main_content, container, false);
		aq = new AQuery(getActivity(), v);
		retrieveData();
		list=(ListView)v.findViewById(R.id.list);
		list.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int pos, long arg3) {
					toastIt("position "+ pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		return v;
	}
	
	@Override
	public void retrieveData() {
		String url = "http://www.masdevid.com/feeds/posts/default";
		indexId = 0;
		listData = new ArrayList<Article>();
        currentTitle = null;
        currentImgUrl = null;
        currentDes = null;
		aq.ajax(url, XmlPullParser.class, new AjaxCallback<XmlPullParser>(){
			
			@Override
			public void callback(String url, XmlPullParser xpp, AjaxStatus status) {
		        
		        try{
			        int eventType = xpp.getEventType();
			        while(eventType != XmlPullParser.END_DOCUMENT) {
			        	if(eventType == XmlPullParser.START_TAG){
			        		String namespace = xpp.getNamespacePrefix(0);
			                String tag = xpp.getName();
			                if ("entry".equals(tag)) {
								currentTitle = "";
								currentDes = "";
								currentImgUrl = "";
							}else if("title".equals(tag)){
			                 	currentTitle = xpp.nextText();
			                }else if("content".equals(tag)){
			                 	currentDes = xpp.nextText();
			                }else if ("thumbnail".equals(tag)) {
			                 	getAttributes(xpp);
							}else if ("total".equals(tag)) {
								addArticle();
							}
			        	}
			        	eventType = xpp.next();
			        }
		        }catch(Exception e){
		                AQUtility.report(e);
		        }
		        showResult(status);
			}

			private void addArticle() {
				Article article = new Article();
				article.setId(indexId);
				article.setDescription(currentDes);
				article.setTitle(currentTitle);
				article.setImgUrl(currentImgUrl);
             	listData.add(article);
			}

			private void getAttributes(XmlPullParser xpp) {
				indexId++;
				int count = xpp.getAttributeCount();
				for (int i = 0; i <count ; i++) {
					if ("url".equals(xpp.getAttributeName(i))) {
						currentImgUrl = xpp.getAttributeValue(i);
					}
				}
			}
		});
	}

	protected void showResult(AjaxStatus status) {
//		adapter=new LazyAdapter(getActivity(), listData);
//		menuItem = new ArrayList<CustomMenuItem>();
//		menuItem.add(new CustomMenuItem(getString(R.string.all_schedules), BaseContentFragment.class));
//		list.setAdapter(new ArrayAdapter<Article>(getActivity(), R.layout.list_menu_items, R.id.menu_text, listData));
		list.setAdapter(new CustomArrayAdapter(getActivity(), R.layout.main_content_item_list, listData)); 
//		list.setAdapter(adapter);
	}
	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeData() {
		// TODO Auto-generated method stub
		
	}

}
