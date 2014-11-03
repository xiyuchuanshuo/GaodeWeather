package com.zky.broadcastreceiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zky.adapter.WeatherAdapter;
import com.zky.model.Weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

public class WeatherReceiver extends BroadcastReceiver{
	private static final String TAG = "WeatherSearch";
	ArrayList<Weather> weathers;
	WeatherAdapter adapter;
	public WeatherReceiver(WeatherAdapter adapter,ArrayList<Weather> weathers){
	this.adapter = adapter;	
	this.weathers = weathers;
	}
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Bundle bundle = arg1.getExtras();
		Log.i(TAG, "获取天气成功============================");
		weathers.clear();
		ArrayList<Weather> weathers2 = bundle.getParcelableArrayList("weather");
		weathers.addAll(weathers2);
		adapter.notifyDataSetChanged();
	}

}
