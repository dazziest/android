package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name = "tMasterEntity")
public class MasterEntity extends Entity {
	
	@TableField(name = "Name", datatype = Entity.DATATYPE_TEXT)
	public String Name = "Spain";
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.Name;
	}
}
