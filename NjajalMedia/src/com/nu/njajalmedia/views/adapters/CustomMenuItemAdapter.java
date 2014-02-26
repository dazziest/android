package com.nu.njajalmedia.views.adapters;

import java.util.List;

import com.nu.njajalmedia.R;
import com.nu.njajalmedia.models.CustomMenuItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomMenuItemAdapter extends ArrayAdapter<CustomMenuItem> {

	private List<CustomMenuItem> listMenuItems;
//	private int rowResourceId;
	private Context context;
//	private ImageLoader imageLoader;

	public CustomMenuItemAdapter(Context context, int textViewResourceId, List<CustomMenuItem> menuItemList) {
		super(context, textViewResourceId, menuItemList);
		this.context = context;
        this.listMenuItems = menuItemList;
//        imageLoader=new ImageLoader(context.getApplicationContext());
//        this.rowResourceId = textViewResourceId;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		CustomMenuItem menuItem = listMenuItems.get(position);
		if (menuItem.getClassName() != null) {
			convertView = inflater.inflate(R.layout.list_menu_items, parent, false);
			setFragmentMenu(convertView, menuItem);
		}else if (menuItem.getMrno() != null) {
//			convertView = inflater.inflate(R.layout.list_menu_info, parent, false);
//			setPatientInfo(convertView, menuItem);
		}else {
			convertView = inflater.inflate(R.layout.list_menu_items, parent, false);
			setAdditionalMenu(convertView, menuItem);
		}
		
		return convertView;
		
	}

	private void setAdditionalMenu(View convertView, CustomMenuItem menuItem) {
		TextView name = (TextView) convertView.findViewById(R.id.menu_text);
		name.setText(menuItem.getMenuName());
	}

//	private void setPatientInfo(View convertView, CustomMenuItem menuItem) {
//		TextView name = (TextView) convertView.findViewById(R.id.tv_patient_name);
//		TextView mrno = (TextView) convertView.findViewById(R.id.tv_mr_no);
//		name.setText(menuItem.getPatientName());
//		mrno.setText(menuItem.getMrno());
//		convertView.setEnabled(false);
//	}

	private void setFragmentMenu(View convertView, CustomMenuItem menuItem) {
		TextView name = (TextView) convertView.findViewById(R.id.menu_text);
		name.setText(menuItem.getMenuName());
	}
	
//	@Override
//    public boolean isEnabled(int position) {
//       return false;
//    }
}
