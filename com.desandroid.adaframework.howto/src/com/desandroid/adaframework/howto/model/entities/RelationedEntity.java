package com.desandroid.adaframework.howto.model.entities;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name = "tRelationedEntity")
public class RelationedEntity extends Entity {

	@TableField(name = "value", datatype = DATATYPE_TEXT)
	public String Value = "";
	
	@TableField(name = "master", datatype = DATATYPE_ENTITY_REFERENCE)
	public MasterEntity Master;
}
