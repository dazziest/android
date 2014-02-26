package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name= "tDependantEntityA")
public class DependantEntityA extends Entity {
	
	@TableField(name = "valueA", datatype = DATATYPE_INTEGER)
	public int Value = 2;
}
