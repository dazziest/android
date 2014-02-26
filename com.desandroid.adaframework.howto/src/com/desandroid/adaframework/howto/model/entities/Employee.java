package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.adaframework.howto.R;
import com.desandroid.adaframework.howto.validators.EmailValidator;
import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.CustomValidation;
import com.desandroid.framework.ada.annotations.Databinding;
import com.desandroid.framework.ada.annotations.RangeValidation;
import com.desandroid.framework.ada.annotations.RegularExpressionValidation;
import com.desandroid.framework.ada.annotations.RequiredFieldValidation;

public class Employee extends Entity {
	@Databinding(ViewId = R.id.EmployeeName)
	@RequiredFieldValidation(messageResourceId = R.string.validiomMessageEmployeeNameRequired)
	@RegularExpressionValidation(expression = "[a-z]*", messageResourceId = R.string.validiomMessageEmployeeNameFormat)
	private String name = null;
	
	@Databinding(ViewId = R.id.EmployeeSurname)
	@RequiredFieldValidation(messageResourceId = R.string.validiomMessageEmployeeSurnameRequired)
	private String surname = null;
	
	@Databinding(ViewId = R.id.EmployeeAge)
	@RequiredFieldValidation(messageResourceId = R.string.validiomMessageEmployeeAgeRequired)
	@RangeValidation(minValue = 1, maxValue = 99, messageResourceId = R.string.validiomMessageEmployeeAgeRange)
	private Integer age = null;
	
	@Databinding(ViewId = R.id.EmployeeEmail)
	@CustomValidation(validator = EmailValidator.class, messageResourceId = R.string.validiomMessageEmployeeEmailFormat)
	private String email = null;
	
	@Databinding(ViewId = R.id.EmployeeActive)
	private Boolean active = null;
	
	public String getName() {
		return name;
	}
	public void setName(String pName) {
		this.name = pName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String pSurname) {
		this.surname = pSurname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String pEmail) {
		this.email = pEmail;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer pAge) {
		this.age = pAge;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean pActive) {
		this.active = pActive;
	}
}
