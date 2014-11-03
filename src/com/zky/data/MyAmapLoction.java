package com.zky.data;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;


public class MyAmapLoction implements AMapLocationListener{
	private LocationManagerProxy mLocationManagerProxy;
	private Context context;
	public MyAmapLoction(Context context) {
		this.context = context;
		mLocationManagerProxy = LocationManagerProxy.getInstance(context);
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, -1, 15, this);
		mLocationManagerProxy.setGpsEnable(false);
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (amapLocation != null
				&& amapLocation.getAMapException().getErrorCode() == 0) {
			// 获取位置信息
			Double geoLat = amapLocation.getLatitude();
			Double geoLng = amapLocation.getLongitude();
			
			
			Toast.makeText(context, geoLat +"  "+geoLng, Toast.LENGTH_SHORT).show();
		}
		
	}

	
	
	
}
