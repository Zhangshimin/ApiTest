package com.ezijing.qa.util;

import java.util.EnumMap;

import com.ezijing.qa.io.IRegionCoordinate;

public class RegionCoordinateImpl implements IRegionCoordinate {

	public EnumMap<RegionProperty, Object> getRegionInfo(int[] region) {
		// TODO Auto-generated method stub
		EnumMap<RegionProperty,Object> returnMap = new EnumMap<RegionProperty,Object>(RegionProperty.class);
		returnMap.put(RegionProperty.FIRSTROW, region[0]);
		returnMap.put(RegionProperty.FIRSTCOL,region[1]);
		returnMap.put(RegionProperty.LENGTH,region[2]);
		return returnMap;
	}

}
