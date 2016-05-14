package com.ezijing.qa.util;

import java.util.EnumMap;

import com.ezijing.qa.io.IRegionCoordinate;
import com.ezijing.qa.io.IRegionCoordinate.RegionProperty;

public class TestRegion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IRegionCoordinate regioncoord = new RegionCoordinateImpl();
		int[] a = null;
		EnumMap<RegionProperty,Object> regioninfo = regioncoord.getRegionInfo(a);
		regioninfo.entrySet().iterator();
		System.out.println(regioninfo.get(IRegionCoordinate.RegionProperty.FIRSTCOL));
		System.out.println(regioninfo.get(IRegionCoordinate.RegionProperty.FIRSTROW));
		System.out.println(regioninfo.get(IRegionCoordinate.RegionProperty.LENGTH));
	}

}
