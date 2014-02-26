package com.desandroid.adaframework.howto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.BaseEntity;
import com.desandroid.adaframework.howto.model.entities.DependantEntityA;
import com.desandroid.adaframework.howto.model.entities.DependantEntityB;

public class dependenciesActivity extends Activity {
	/**
	 * Activity DataContext.
	 */
	private ApplicationDataContext appDataContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		try {
			
			setContentView(R.layout.dependencies_activity);
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
					BaseEntity baseEntity = new BaseEntity();
					DependantEntityA dependantEntityA = new DependantEntityA();
					DependantEntityB dependantEntityB = new DependantEntityB();
					
					baseEntity.EntityA = dependantEntityA;
					baseEntity.EntityB.add(dependantEntityB);
					
					appDataContext.BaseEntitySet.add(baseEntity);
					appDataContext.BaseEntitySet.save();
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
