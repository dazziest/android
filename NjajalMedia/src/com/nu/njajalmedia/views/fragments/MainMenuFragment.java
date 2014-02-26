package com.nu.njajalmedia.views.fragments;

import java.util.ArrayList;
import java.util.List;

import com.nu.njajalmedia.R;
import com.nu.njajalmedia.models.CustomMenuItem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Provide main menu function
 * @author muhammad_a
 *
 */
public class MainMenuFragment extends Fragment {
	
	protected LinearLayout leftMenuLayout;
	protected OnMainMenuClickListener menuListener;
//	protected TextView btnLogout;
	protected List<CustomMenuItem> menuItem;
	protected ListView listMenu;
	protected int currentMenuPosition;
//	protected LinearLayout llPatientSchedule;
	
//	private TextView btnAdvandSearch;
//	private Intent searchIntent;
//	private EditText inputSearch;
//	protected TextView txtUserName;
//	protected TextView txtUserType;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		leftMenuLayout = (LinearLayout)inflater.inflate(R.layout.menu_main_fragment, null);
//		inputSearch = (EditText) leftMenuLayout.findViewById(R.id.actv_search);
//		btnLogout = (TextView)leftMenuLayout.findViewById(R.id.btn_logout);
		listMenu = (ListView) leftMenuLayout.findViewById(R.id.list_menu_fragment_main);
//		txtUserName = (TextView) leftMenuLayout.findViewById(R.id.tv_user_name);
//		txtUserType = (TextView) leftMenuLayout.findViewById(R.id.tv_user_type);
//		txtUserName.setText(LoginInfo.getUserName());
//		txtUserType.setText(LoginInfo.getUserType());
		
//		btnLogout.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				logout();
//			}
//		});
//		btnAdvandSearch = (TextView) leftMenuLayout.findViewById(R.id.tv_advand_search);
//		btnAdvandSearch.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
////				searchIntent = new Intent(getActivity(), AdvancedSearchActivity.class);
////				searchIntent.putExtra("queryName", inputSearch.getText().toString());
////				startActivityForResult(searchIntent, getResources().getInteger(R.integer.pick_form_adv_search_result));
////				menuListener.onClickMainMenu();
////				listMenu.setItemChecked(currentMenuPosition, false);
//			}
//		});
		
		menuItem = new ArrayList<CustomMenuItem>();
		menuItem.add(new CustomMenuItem(getString(R.string.all_schedules), BaseContentFragment.class));
		listMenu.setAdapter(new ArrayAdapter<CustomMenuItem>(getActivity(), R.layout.list_menu_items, R.id.menu_text, menuItem));
		listMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
				try {
					CustomMenuItem menuItemFragment = (CustomMenuItem) adapter.getItemAtPosition(pos);
					if(menuItemFragment.getClassName() != null) {
						menuListener.onSelectFragment(menuItemFragment.getClassName().newInstance(), pos);
					}else {
						Toast.makeText(getActivity(), "Not yet implemented", Toast.LENGTH_LONG).show();
					}
					currentMenuPosition = pos;
					listMenu.setItemChecked(currentMenuPosition, true);
				} catch (java.lang.InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		this.currentMenuPosition = 1;
		listMenu.setItemChecked(currentMenuPosition, true);
		
		return leftMenuLayout;
	}
	
	/*
	 * Redirect to LoginActivity
	 */
	public void logout() {
		//clear login token
		SharedPreferences myPrefs = getActivity().getSharedPreferences(getString(R.string.pref_mms), 0);
		myPrefs.edit().clear().commit();
//		Intent intent = new Intent(getActivity(), LoginActivity.class);
//		startActivity(intent);
		this.getActivity().finish();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
		
	public interface OnMainMenuClickListener{
		public void onClickMainMenu();
		public void onSelectFragment(Fragment fragment, int pos);
		public void onSaveAllChanges();
		public void onResetAll();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			menuListener = (OnMainMenuClickListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMainMenuClickListener");
		}
	}
	
}
