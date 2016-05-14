package com.ezijing.qa.io;

import java.util.EnumMap;

public interface IRegionCoordinate {

	enum RegionProperty{
		FIRSTROW, FIRSTCOL,LENGTH
	}
	public EnumMap<RegionProperty,Object> getRegionInfo(int[] region);
}
