package com.nu.njajalmedia.models;

import android.support.v4.app.Fragment;

/**
 * 
 * Menu Item Model
 *
 */
public class CustomMenuItem extends BaseModel{
	private String name;
	private Class<? extends Fragment> cls;
	private String patientName;
	private String mrno;
	public CustomMenuItem(String name, Class<? extends Fragment> cls) {
		this.name = name;
		this.cls = cls;
	}
	
	public CustomMenuItem(String patientName, String mrno){
		this.patientName = patientName;
		this.mrno = mrno;
	}
	
	public CustomMenuItem(String name){
		this.name = name;
	}
	
	public String getPatientName() {
		return this.patientName;
	}
	
	public String getMrno() {
		return this.mrno;
	}
	
	public String getMenuName(){
		return this.name;
	}
	
	public Class<? extends Fragment> getClassName(){
		return this.cls;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
