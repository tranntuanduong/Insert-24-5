package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {

	@Override
	public BuildingEntity save(BuildingDTO newBuilding) {
		 BuildingConverter buildingConverter = new BuildingConverter();
		 BuildingEntity BuildingEntity = buildingConverter.convertToEntity(newBuilding);
		  
		return BuildingEntity;
	}

}
