package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name = "tBaseInheritedEntity")
public class BaseInheritedEntity extends Entity {
	
	@TableField(name = "baseValue", datatype = DATATYPE_INTEGER)
	public int BaseValue = 1;
}
