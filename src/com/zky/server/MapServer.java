package com.zky.server;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.zky.data.MyAmapLoction;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;

public class MapServer  extends Service implements AMapLocationListener{
	private LocationManagerProxy mLocationManagerProxy;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, -1, 15, this);
		mLocationManagerProxy.setGpsEnable(false);
		
		
		super.onCreate();
	}




	@Override
	public void onDestroy() {
		
		
		super.onDestroy();
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
		// TODO Auto-generated method stub
		if (amapLocation != null
				&& amapLocation.getAMapException().getErrorCode() == 0) {
			// 获取位置信息
//			Double geoLat = amapLocation.getLatitude();
//			Double geoLng = amapLocation.getLongitude();
			
			
			
		}
		
	}

}
