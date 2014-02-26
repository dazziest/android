package com.desandroid.adaframework.howto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class mainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onCLick(View pView) {
		try{
			if (pView != null) {
				switch (pView.getId()) {
					case R.id.executeDependenciesDemo:
						Intent executeDependenciesIntent = new Intent(this, dependenciesActivity.class);
						this.startActivity(executeDependenciesIntent);
						break;
					case R.id.executeInheritanceDemo:
						Intent executeInheritanceIntent = new Intent(this, inheritanceActivity.class);
						this.startActivity(executeInheritanceIntent);
						break;
					case R.id.executeRelationshipsDemo:
						Intent executeRelationshipsIntent = new Intent(this, relationshipsActivity.class);
						this.startActivity(executeRelationshipsIntent);
						break;
					case R.id.executeListViewDemo:
						Intent executeListViewIntent = new Intent(this, listActivity.class);
						this.startActivity(executeListViewIntent);
						break;
					case R.id.executePerformanceDemo:
						Intent executePerformanceIntent = new Intent(this, performanceActivity.class);
						this.startActivity(executePerformanceIntent);
						break;
					case R.id.executeAsyncTaskDemo:
						Intent executeAsyncTaskIntent = new Intent(this, asyncTaskActivity.class);
						this.startActivity(executeAsyncTaskIntent);
						break;
					case R.id.executeSearchDemo:
						Intent executeSearchDemoIntent = new Intent(this, searchActivity.class);
						this.startActivity(executeSearchDemoIntent);
						break;
					case R.id.executeDatabindingDemo:
						Intent executeDatabindingDemoIntent = new Intent(this, databindingActivity.class);
						this.startActivity(executeDatabindingDemoIntent);
						break;
					case R.id.executeValidationDemo:
						Intent executeValidationDemoIntent = new Intent(this, validationsActivity.class);
						this.startActivity(executeValidationDemoIntent);
						break;
				}
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}