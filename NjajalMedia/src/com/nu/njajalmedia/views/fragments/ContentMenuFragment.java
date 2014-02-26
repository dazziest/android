package com.nu.njajalmedia.views.fragments;

import java.util.ArrayList;
import java.util.List;

import com.nu.njajalmedia.R;
import com.nu.njajalmedia.models.CustomMenuItem;
import com.nu.njajalmedia.views.activities.MainActivity;
import com.nu.njajalmedia.views.adapters.CustomMenuItemAdapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ContentMenuFragment extends MainMenuFragment {

	private List<CustomMenuItem> menuItem;
	private ListView listMenu;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		leftMenuLayout = (LinearLayout) inflater.inflate(R.layout.menu_content_fragment, null);
//		btnLogout = (TextView) leftMenuLayout.findViewById(R.id.btn_logout);
		listMenu = (ListView) leftMenuLayout.findViewById(R.id.list_menu_fragment);	
//		txtUserName = (TextView) leftMenuLayout.findViewById(R.id.tv_user_name);
//		txtUserType = (TextView) leftMenuLayout.findViewById(R.id.tv_user_type);
//		txtUserName.setText(LoginInfo.getUserName());
//		txtUserType.setText(LoginInfo.getUserType());
		
		menuItem = new ArrayList<CustomMenuItem>();
//		menuItem.add(new CustomMenuItem(getString(R.string.all_schedules), PatientSchedulesFragment.class));
//		menuItem.add(new CustomMenuItem(PatientInfo.getPatientName(), PatientInfo.getMrNo()));
//		menuItem.add(new CustomMenuItem(getString(R.string.patient_details), PatientDetailsFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.patient_med_history), PatientMedFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.patient_case_history), PatientCaseFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.vital_sign_measurement), VitalSignMeasurementFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.examination_details), ExaminationDetailFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.body_chart), BodyChartFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.medical_certificate), MedicalCertificateFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.supporting_document), SupportingDocumentFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.med_fee_sum), MedFeeSumFragment.class));
//		menuItem.add(new CustomMenuItem(getString(R.string.save_all_changes)));
//		menuItem.add(new CustomMenuItem(getString(R.string.reset_all)));
		listMenu.setAdapter(new CustomMenuItemAdapter(getActivity(), R.layout.list_menu_items, menuItem));
		listMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
				try {
					CustomMenuItem menuItemFragment = (CustomMenuItem) adapter.getItemAtPosition(pos);
					String name = menuItemFragment.getMenuName();
					if (currentMenuPosition == pos) {
						menuListener.onClickMainMenu();
					}
//					else if (menuItemFragment.getClassName() == PatientSchedulesFragment.class) {
//						showAllSchedules();
//					}
					else if(menuItemFragment.getClassName() != null) {
						menuListener.onSelectFragment((menuItemFragment.getClassName()).newInstance(), currentMenuPosition);
						currentMenuPosition = pos;
					}else if (name == null) {
						menuListener.onClickMainMenu();
					}
//					else if (name.equalsIgnoreCase(getString(R.string.save_all_changes))) {
//						menuListener.onSaveAllChanges();
//						listMenu.setItemChecked(currentMenuPosition, true);
//					}
//					else if (menuItemFragment.getMenuName().equalsIgnoreCase(getString(R.string.reset_all))) {
//						menuListener.onResetAll();
//						listMenu.setItemChecked(currentMenuPosition, true);
//					}
				} catch (java.lang.InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
//
		this.currentMenuPosition = 2;
		listMenu.setItemChecked(currentMenuPosition, true);
		
//		btnLogout.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				SharedPreferences myPrefs = getActivity().getSharedPreferences(getString(R.string.pref_mms), 0);
//				
//				//Clear shared preferences
//				myPrefs.edit().clear().commit();
//				logout();
//			}
//		});

		return leftMenuLayout;
	}

//	private void showAllSchedules() {
//		Intent intent = new Intent(getActivity(), MainActivity.class);
//		startActivity(intent);
//		getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//		getActivity().finish();
//	}

	public void setCheckedPosition(int pos) {
		listMenu.setItemChecked(pos, true);
		currentMenuPosition = pos;
	}
	
	
}
