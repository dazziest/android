package com.desandroid.adaframework.howto;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.desandroid.adaframework.howto.model.entities.Employee;
import com.desandroid.framework.ada.DataBinder;
import com.desandroid.framework.ada.validators.ValidationResult;

public class validationsActivity extends Activity{
	private Employee employee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			
			setContentView(R.layout.validations_activity);
			this.employee = new Employee();
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
		
	public void ValidateCommand(View pView) {
		try {
			
			this.employee.bind(findViewById(R.id.EmployeeDataForm), DataBinder.BINDING_UI_TO_ENTITY);
			
			if (!this.employee.Validate(this)) {
				List<ValidationResult> validationResults = this.employee.getValidationResult();
				
				String message = "";
				for(ValidationResult result : validationResults) {
					message += "\r\n * " + result.getMessage();
				}
				
				Toast.makeText(this, String.format("The next filed is not correct: %s", message), Toast.LENGTH_SHORT).show();
				
			} else {
				Toast.makeText(this, "All OK", Toast.LENGTH_SHORT).show();
			}
			
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}
}
