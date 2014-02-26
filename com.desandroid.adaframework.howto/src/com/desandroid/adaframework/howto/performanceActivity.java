package com.desandroid.adaframework.howto;

import java.util.Date;

import com.desandroid.adaframework.howto.model.datacontexts.ApplicationDataContext;
import com.desandroid.adaframework.howto.model.entities.PerformanceTestEntity;
import com.desandroid.adaframework.howto.model.entities.Tools;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;


public class performanceActivity extends Activity {
	
	/**
	 * Activity DataContext.
	 */
	private ApplicationDataContext performanceDataContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		try {
			
			setContentView(R.layout.performance_activity);	
			performanceDataContext = new ApplicationDataContext(this);
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	public void onClick(View pView) {
		try{
			if (pView != null) {
				int numOfIterations = 0;
				
				switch (pView.getId()) {
					case R.id.buttonAdd100:
						numOfIterations = 100;
						break;
					case R.id.buttonAdd500:
						numOfIterations = 500;
						break;
					case R.id.buttonAdd1000:
						numOfIterations = 1000;
						break;
				}
				
				executeTest(numOfIterations);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	
	private void executeTest (int pNumOfIterations) throws Exception {
		if (pNumOfIterations > 0) {
			
			
			for (int counter = 1; counter <= pNumOfIterations; counter++) {
				PerformanceTestEntity entity = new PerformanceTestEntity();
				entity.PropertyA = counter;
				entity.PropertyB = (double)counter;
				entity.PropertyC = (float)((float)counter * 0.1);
				entity.PropertyD = "http://adaframework.com";
				if ((counter % 2) == 0) {
					entity.PropertyF = true;
				} else {
					entity.PropertyF = false;
				}
				
				performanceDataContext.PerformanceEntitySet.add(entity);
			}
			
			Date initOfProcess = new Date();
			performanceDataContext.PerformanceEntitySet.save();
			Date endOfProcess = new Date();
			
			String timeDiference = Tools.calculateTimeDiference(initOfProcess, endOfProcess);
			Toast.makeText(this, String.format("Total Time: %s", timeDiference), Toast.LENGTH_LONG).show();
		}
	}
}
