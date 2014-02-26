package com.desandroid.adaframework.howto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.MasterEntity;

public class listActivity extends Activity{
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
			
			entitiesListAdapter = new ArrayAdapter<MasterEntity>(this, android.R.layout.simple_list_item_1);
			entitiesList.setAdapter(entitiesListAdapter);
			
			//Set the ListView adapter to the ObsetSet.
			appDataContext.MasterEntitySet.setAdapter(entitiesListAdapter);
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onClick(View pView) {
		runDemo();
	}
	
	private void runDemo() {
		try {
			
			if (appDataContext != null) {
				for(int index = 1; index <= 5; index++) {
					MasterEntity entity = new MasterEntity();
					entity.Name = String.format("Entity %d", index);
					appDataContext.MasterEntitySet.add(entity);
				}
			}
			
			Toast.makeText(this, "Fill OK.", Toast.LENGTH_LONG).show();
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
