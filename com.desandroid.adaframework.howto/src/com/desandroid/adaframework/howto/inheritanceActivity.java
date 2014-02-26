package com.desandroid.adaframework.howto;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.InheritedEntity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class inheritanceActivity extends Activity {
	/**
	 * Activity DataContext.
	 */
	private ApplicationDataContext appDataContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			setContentView(R.layout.inheritances_activity);
			appDataContext = new ApplicationDataContext(this);
			
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
				//Force the database creation. This is not needed.
				if (appDataContext.deleteDatabase()){
					InheritedEntity entity = new InheritedEntity();
					entity.BaseValue = 66;
					entity.InheritedValue = 99;
					
					appDataContext.InheritedEntitySet.add(entity);
					appDataContext.InheritedEntitySet.save();
					
				} else {
					Toast.makeText(this, "Database file does not deleted.", Toast.LENGTH_LONG).show();
				}
			}
			
			Toast.makeText(this, "Saved ok.", Toast.LENGTH_LONG).show();
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
