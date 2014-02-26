package com.desandroid.adaframework.howto;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.MasterEntity;
import com.desandroid.adaframework.howto.model.entities.RelationedEntity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class relationshipsActivity extends Activity {
	/**
	 * Activity DataContext.
	 */
	private ApplicationDataContext appDataContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		try {
			
			setContentView(R.layout.relationships_activity);
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
					
					MasterEntity master = new MasterEntity();
					master.Name = "Spain";
					appDataContext.MasterEntitySet.add(master);
					appDataContext.MasterEntitySet.save();
					
					RelationedEntity relationed = new RelationedEntity();
					relationed.Value = "DesAndrOId";
					relationed.Master = appDataContext.MasterEntitySet.get(0);
					
					appDataContext.RelationedEntitySet.add(relationed);
					appDataContext.RelationedEntitySet.save();
					
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
