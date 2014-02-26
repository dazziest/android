package com.desandroid.adaframework.howto;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.desandroid.adaframework.howto.model.entities.Employee;

public class databindingActivity extends Activity{
	private Employee employee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			setContentView(R.layout.databinding_activity);
			
			employee = new Employee();
			employee.setName("DesAndrOId");
			employee.setSurname("Spain");
			employee.setEmail("email@desandroid.com");
			employee.setAge(31);
			employee.setActive(true);
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
		
	public void AdaDatabindCommand(View pView) {
		try {
			
			Date initDate = new Date();
			
			employee.bind(findViewById(R.id.EmployeeDataForm));
			
			Toast.makeText(this,String.format("Binding Time: %s", Tools.calculateTimeDiference(initDate, new Date())), Toast.LENGTH_SHORT).show();
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
