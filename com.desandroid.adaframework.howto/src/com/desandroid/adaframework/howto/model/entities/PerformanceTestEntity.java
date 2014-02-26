package com.desandroid.adaframework.howto.model.entities;

import java.util.Date;

import com.desandroid.framework.ada.Entity;
import com.desandroid.framework.ada.annotations.Table;
import com.desandroid.framework.ada.annotations.TableField;

/**
 * Performance demo entity class.
 * @author DesAndrOId
 *
 */
@Table(name = "tPerformaceEntities")
public class PerformanceTestEntity extends Entity {

	@TableField(name = "propertyA", datatype = DATATYPE_INTEGER)
	public Integer PropertyA = null;
	
	@TableField(name = "propertyB", datatype = DATATYPE_DOUBLE)
	public Double PropertyB = null;
	
	@TableField(name = "propertyC", datatype = DATATYPE_REAL)
	public Float PropertyC = null;
	
	@TableField(name = "propertyD", datatype = DATATYPE_TEXT)
	public String PropertyD = null;
	
	@TableField(name = "propertyE", datatype = DATATYPE_DATE)
	public Date PropertyE = null;
	
	@TableField(name = "propertyF", datatype = DATATYPE_BOOLEAN)
	public Boolean PropertyF = null;
}
