package com.nu.njajalmedia.views.fragments;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.nu.njajalmedia.R;
import com.nu.njajalmedia.helpers.NetworkHelper;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Provide basic function for all fragments
 * 
 * @author muhammad_a
 *
 */
public abstract class BaseContentFragment extends Fragment{
	private String name;
//	protected String oldBaseUrl;
	protected String newBaseUrl;
//	protected DatabaseHelper db;
	protected Dialog dialog;
	private boolean isRefreshInProgress;
	protected boolean isSynchronized;
//	protected List<BodyChartLookup> listBodyLookup;
	
	/**
	 * Handle action bar action
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * check if content is synchronized to server
	 */
	public boolean isSynchronize(){
		return this.isSynchronized;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		oldBaseUrl = getString(R.string.temp_service_url);
		newBaseUrl = getString(R.string.temp_ios_service_url);
		isSynchronized = true;
		return container;
	}
	
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		//update progress bar on action bar
//		MenuItem menuRefresh = menu.findItem(R.id.menu_refresh);
//		MenuItem menuProgressBar = menu.findItem(R.id.menu_progressbar);
//		if (isRefreshInProgress) {
//			menuProgressBar.setVisible(true);
//			menuRefresh.setVisible(false);
//		} else {
//			menuProgressBar.setVisible(false);
//			menuRefresh.setVisible(true);
//		}
	}
	
	/**
	 * show error page on certain view
	 * @param view
	 */
	protected void showErrorPage(View v){
		showErrorPage(v, NetworkHelper.getConnectionInfo(getActivity()));
	}
	
	/**
	 * show error page on certain view with string info
	 * @param view
	 */
	protected void showErrorPage(View v, String info){
		hideProgressBar();
		AQuery aq = new AQuery(getActivity(), v);
//		aq.id(R.id.progress_layout).visibility(View.GONE);
//		aq.id(R.id.table).visibility(View.GONE);
//		aq.id(R.id.error_page_layout).visibility(View.VISIBLE);
//		aq.id(R.id.error_page_info).text(info);
//		aq.id(R.id.error_page_button).visible().clicked(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				retrieveData();
//			}
//		});
		isSynchronized = false;
	}
	
	/**
	 * hide progress bar
	 */
	protected void hideProgressBar(){
		isRefreshInProgress = false;
		getActivity().invalidateOptionsMenu();
		isSynchronized = true;
	}
	
	/**
	 * hide progress bar page on certain view
	 * @param view
	 */
	protected void hideProgressBar(View v){
		hideProgressBar();
		AQuery aq = new AQuery(getActivity(), v);
//		aq.id(R.id.progress_layout).visibility(View.GONE);
//		aq.id(R.id.table).visibility(View.VISIBLE);
//		aq.id(R.id.error_page_layout).visibility(View.GONE);
	}
	
	/**
	 * show progress bar
	 */
	protected void showProgressBar(){
		isRefreshInProgress = true;
		getActivity().invalidateOptionsMenu();
	}
	
	/**
	 * show progress bar page on certain view
	 * @param view
	 */
	protected void showProgressBar(View v){
		showProgressBar();
		AQuery aq = new AQuery(getActivity(), v);
//		aq.id(R.id.progress_layout).visibility(View.VISIBLE);
//		aq.id(R.id.table).visibility(View.GONE);
//		aq.id(R.id.error_page_layout).visibility(View.GONE);
	}

	/**
	 * Set resource data from main activity to current fragment
	 * @param context
	 * @param db
	 * @param data
	 */
//	public abstract void setResourceData(Context context, DatabaseHelper db, Map<String, Object> data);
	
	
	public abstract void retrieveData();
	
	public abstract void saveData();
	
	public abstract void initializeData(); 
	
	/*
	 * set title
	 */
	protected void setActionBarTitle(String menuName) {
		this.name = menuName;
		String title = getString(R.string.app_name) + " - " + name;
		getActivity().getActionBar().setTitle(title);
	}
	
	public String getFragmentName(){
		return this.name;
	}
	
	public void toastIt(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
	}
	
	protected String getMonthName(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	/**
	 * Set spinner adapter from List DropDownMenu
	 * @param spinner
	 * @param sourceList
	 */
//	protected void setSpinnerAdapter(Spinner spinner, List<DropDownMenu> sourceList) {
//		DropDownMenuHelper.setSpinnerAdapter(getActivity(), spinner, sourceList);
//	}
	
	/**
	 * Get value from spinner
	 * @param input
	 * @return string
	 */
//	protected String getValue(Spinner input) {
//		if (input == null) {
//			return "-";
//		}
//		DropDownMenu menu = (DropDownMenu) input.getSelectedItem();
//		if (menu == null || menu.getId() == null) {
//			return "-";
//		}else {
//			return menu.getId();
//		}
//	}
	/**
	 * Get value from EditText
	 * @param input
	 * @return string
	 */
	protected String getValue(EditText input) {
		if (input == null || input.getText().toString().isEmpty()) {
			return "-";
		}else {
			return input.getText().toString();
		}
	}
	/**
	 * Get value from CheckBox
	 * @param input
	 * @return string
	 */
	protected String getValue(CheckBox input) {
		if (input == null) {
			return "false";
		}
		return Boolean.toString(input.isChecked());
	}
	
	/**
	 * Get double value from EditText
	 * @param value
	 * @return double
	 */
	protected double getDoubleValue(EditText value) {
		try {
			String numValue = value.getText().toString();
			if (numValue.isEmpty()) {
				return 0;
			}else {
				return Double.parseDouble(numValue);
			}			
		} catch (Exception e) {
			Log.e("Error parsing input", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Get double value from Spinner
	 * @param value
	 * @return double
	 */
	protected double getDoubleValue(Spinner value) {
		try {
			return Double.parseDouble(value.getSelectedItem().toString());
		} catch (Exception e) {
			Log.e("Error parsing input", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Get integer value from String
	 * @param value
	 * @return int
	 */
	protected int getIntegerValue(String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			Log.e("Error parsing input", e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Show popup dialog
	 * @param title
	 * @param v
	 */
	protected void showPopupDialog(String title, View v) {
		hideProgressBar(v);
		dialog = new Dialog(getActivity());
		dialog.setContentView(v);
		dialog.setTitle(title);
		dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		dialog.show();
		dialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				//TODO: Delete input data of onTableClick listener
//				onTableClick(null);
			}
		});
	}
}
