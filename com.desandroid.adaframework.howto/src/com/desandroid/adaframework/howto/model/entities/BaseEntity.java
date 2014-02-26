package com.desandroid.adaframework.howto.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

@Table(name = "tBaseEntity")
public class BaseEntity extends Entity {

	@TableField(name = "baseValue", datatype = DATATYPE_INTEGER)
	public int BaseValue = 1;

	@TableField(name = "entityA", datatype = DATATYPE_ENTITY)
	public DependantEntityA EntityA = null;
	
	@TableField(name = "entityB", datatype = DATATYPE_ENTITY)
	public List<DependantEntityB> EntityB = null;
	
	public BaseEntity() {
		this.EntityA = new DependantEntityA();
		this.EntityB = new ArrayList<DependantEntityB>();
	}
}
