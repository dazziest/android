package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name = "tInheritedEntity")
public class InheritedEntity extends BaseInheritedEntity {
	
	@TableField(name = "InheritedValue", datatype = DATATYPE_INTEGER)
	public int InheritedValue = 4;
}
