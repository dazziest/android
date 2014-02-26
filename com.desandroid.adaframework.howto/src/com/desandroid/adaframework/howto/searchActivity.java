package com.desandroid.adaframework.howto;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.MasterEntity;
import com.desandroid.framework.ada.exceptions.AdaFrameworkException;

public class searchActivity extends Activity{
	/**
	 * Activity DataContext.
	 */
	private ApplicationDataContext appDataContext;
	private ListView entitiesList;
	
	private ArrayAdapter<MasterEntity> entitiesListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			setContentView(R.layout.list_activity);
			entitiesList = (ListView)findViewById(R.id.entitiesList);
			appDataContext = new ApplicationDataContext(this);
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onClick(View pView) {
		try {
	
			runDemo();
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	private void populatetable() throws AdaFrameworkException {
		if (appDataContext != null) {
			appDataContext.MasterEntitySet.fill();
			
			if (appDataContext.MasterEntitySet.size() == 0) {
				for(int index = 1; index <= 5; index++) {
					MasterEntity entity = new MasterEntity();
					entity.Name = String.format("Entity %d", index);
					appDataContext.MasterEntitySet.add(entity);
				}
				appDataContext.MasterEntitySet.save();
			}
		}
	}
	
	private void runDemo() throws AdaFrameworkException {

		populatetable();
		
		List<MasterEntity> masterEntityList = appDataContext.MasterEntitySet.search(null, null, null, null, null, null, null, null);
		if (masterEntityList != null) {
			entitiesListAdapter = new ArrayAdapter<MasterEntity>(this, android.R.layout.simple_list_item_1, masterEntityList);
			entitiesList.setAdapter(entitiesListAdapter);
		}
		
		Toast.makeText(this, "Fill OK.", Toast.LENGTH_LONG).show();
	}
	
}
