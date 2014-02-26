package com.desandroid.adaframework.howto.model.datacontexts;

import android.content.Context;

import com.desandroid.adaframework.howto.model.entities.BaseEntity;
import com.desandroid.adaframework.howto.model.entities.InheritedEntity;
import com.desandroid.adaframework.howto.model.entities.MasterEntity;
import com.desandroid.adaframework.howto.model.entities.PerformanceTestEntity;
import com.desandroid.adaframework.howto.model.entities.RelationedEntity;
import com.desandroid.framework.ada.ObjectContext;
import com.desandroid.framework.ada.ObjectSet;

/**
 * Performance test ObjectContext.
 * @author DesAndrOId
 */
public class ApplicationDataContext extends ObjectContext {

	/**
	 * Performance Entities ObjectSet.
	 */
	public ObjectSet<PerformanceTestEntity> PerformanceEntitySet;
	
	
	public ObjectSet<BaseEntity> BaseEntitySet;
	
	
	public ObjectSet<InheritedEntity> InheritedEntitySet;
	
	
	public ObjectSet<MasterEntity> MasterEntitySet;
	
	public ObjectSet<RelationedEntity> RelationedEntitySet;
	
	/**
	 * Principal Constructor of the ObjectContext.
	 * @param pContext
	 * @throws Exception
	 */
	public ApplicationDataContext(Context pContext) throws Exception {
		super(pContext);
		
		this.InheritedEntitySet = new ObjectSet<InheritedEntity>(InheritedEntity.class, this);
		this.BaseEntitySet = new ObjectSet<BaseEntity>(BaseEntity.class, this);
		this.PerformanceEntitySet = new ObjectSet<PerformanceTestEntity>(PerformanceTestEntity.class, this);
		
		this.MasterEntitySet = new ObjectSet<MasterEntity>(MasterEntity.class, this);
		this.RelationedEntitySet = new ObjectSet<RelationedEntity>(RelationedEntity.class, this);
	}
}
