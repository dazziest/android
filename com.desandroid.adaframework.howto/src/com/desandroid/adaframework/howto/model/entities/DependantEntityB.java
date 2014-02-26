package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name= "tDependantEntityB")
public class DependantEntityB extends Entity {
	
	@TableField(name = "valueB", datatype = DATATYPE_INTEGER)
	public int Value = 3;
}
