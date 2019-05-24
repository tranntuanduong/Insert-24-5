package com.laptrinhjavaweb.repositoty.impl;


import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository{

	@Override
	public Long insert(BuildingEntity buildingEntity) {
		
		String sql = Query.queryInsert(buildingEntity.getClass());
		Object[] parameters = (Object[]) RunGetter.runGetter(buildingEntity);
		System.out.println(sql);
		
		return this.insert(sql, parameters);
	}

}
