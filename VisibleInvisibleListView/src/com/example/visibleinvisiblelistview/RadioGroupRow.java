package com.example.visibleinvisiblelistview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioGroupRow extends ListActivity {
	 
	  private List<RowData> mData = new ArrayList<RowData>();
	 
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			for (int i = 0; i < 40; i++) {
				RowData rd = new RowData();
				rd.mText = "Row no." + i;
				mData.add(rd);
			}
			setListAdapter(new RGAdapter(this, mData));
		}
	 
		private static class RGAdapter extends BaseAdapter {
	 
			private LayoutInflater mInflater;
			private List<RowData> mData;
	 
			public RGAdapter(Context context, List<RowData> data) {
				mInflater = LayoutInflater.from(context);
				mData = data;
			}
	 
			public List<RowData> getData() {
				// you'll use this method to get all of the row's data items. Then
				// using the checkedPosition fields from RowData you can see which
				// of the RadioButton from that rows RadioGroup is checked.
				return mData;
			}
	 
			@Override
			public int getCount() {
				return mData.size();
			}
	 
			@Override
			public RowData getItem(int position) {
				return mData.get(position);
			}
	 
			@Override
			public long getItemId(int position) {
				return position;
			}
	 
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = mInflater.inflate(R.layout.adapters_rgrow,
							parent, false);
				}
				RowData rd = getItem(position);
				// set other views from your row
				((TextView) convertView.findViewById(R.id.text)).setText(rd.mText);
				RadioGroup rg = (RadioGroup) convertView.findViewById(R.id.rg);
				rg.setOnCheckedChangeListener(null);
				// check the correct RadioButton from the Radiogroup
				rg.check(RowData.ROW_IDS[rd.checkedPosition]);
				rg.setTag(Integer.valueOf(position));
				rg.setOnCheckedChangeListener(mListener);
				return convertView;
			}
	 
			private RadioGroup.OnCheckedChangeListener mListener = new RadioGroup.OnCheckedChangeListener() {
	 
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					Integer realPosition = (Integer) group.getTag();
					RowData rd = mData.get(realPosition);
					// convert the Button id into a simple int value
					for (int i = 0; i < RowData.ROW_IDS.length; i++) {
						if (checkedId == RowData.ROW_IDS[i]) {
							rd.checkedPosition = i;
							break;
						}
					}
				}
			};
	 
		}
	 
		private static class RowData {
			static final int NO_CHECK = -1;
			static final int[] ROW_IDS = { R.id.radio0, R.id.radio1, R.id.radio2,
					NO_CHECK };
			String mText;
			// I assumed we start initially with the first RadioButton in each row
			// checked(but you could make checkedPosition to be 3 meaning NO_CHECK)
			int checkedPosition = 0; // this will let you know which RadioButton is
										// checked for a particular row
		}
	 
	}