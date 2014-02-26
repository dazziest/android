package com.desandroid.adaframework.howto;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.MasterEntity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class asyncTaskActivity extends Activity {

	/**
	 * Activity DataContext.
	 */
	private ApplicationDataContext appDataContext;
	
	private ProgressDialog loadingDialog;
	private ListView entitiesList;
	private ArrayAdapter<MasterEntity> entitiesListAdapter;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		try {
			
			setContentView(R.layout.list_activity);
			
			loadingDialog = new ProgressDialog(this);
	    	loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    	loadingDialog.setCancelable(false);
	    	loadingDialog.setMessage("Loading, please wait...");
	    	
	    	
	    	appDataContext = new ApplicationDataContext(this);
	    	
	    	entitiesList = (ListView)findViewById(R.id.entitiesList);
	    	entitiesListAdapter = new ArrayAdapter<MasterEntity>(this, android.R.layout.simple_list_item_1);
			entitiesList.setAdapter(entitiesListAdapter);
			
			//Set the ListView adapter to the ObsetSet.
			appDataContext.MasterEntitySet.setAdapter(entitiesListAdapter);
	    	
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onClick(View pView) {
		loadingDialog.show();
		new AsyncProcess().execute(null);
	}
	
	class AsyncProcess extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			try {
				appDataContext.MasterEntitySet.clear();
				
				for(int counter = 1; counter <= 5; counter++) {
					MasterEntity entity = new MasterEntity();
					entity.Name = String.format("Entity %d", counter);
					
					appDataContext.MasterEntitySet.add(entity);
					Thread.sleep(1000, 0);
				}
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return null;
		}
		
		
		@Override
		protected void onPostExecute(Void result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					loadingDialog.dismiss();
				}
			});
		}
	}
}
