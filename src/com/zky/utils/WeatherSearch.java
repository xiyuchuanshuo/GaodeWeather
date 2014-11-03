package com.zky.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocalDayWeatherForecast;
import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;
import com.zky.data.ConstantData;
import com.zky.model.Weather;

public  class WeatherSearch implements AMapLocalWeatherListener {
	private static final String TAG = "WeatherSearch";
	private  LocationManagerProxy mLocationManagerProxy;
	 Context context;
	 SharedPreferences sharedPreferences;
	public WeatherSearch(Context context) {
		this.context = context;
		sharedPreferences = context.getSharedPreferences(ConstantData.SHAREPERFERSNAME, Context.MODE_PRIVATE);
		mLocationManagerProxy = LocationManagerProxy.getInstance(context);
		mLocationManagerProxy.requestWeatherUpdates(
				LocationManagerProxy.WEATHER_TYPE_LIVE, this);
		mLocationManagerProxy.requestWeatherUpdates(
				LocationManagerProxy.WEATHER_TYPE_FORECAST, this);
	}

	@Override
	public void onWeatherForecaseSearched(
			AMapLocalWeatherForecast aMapLocalWeatherForecast) {
		if (aMapLocalWeatherForecast != null
				&& aMapLocalWeatherForecast.getAMapException().getErrorCode() == 0) {
			List<AMapLocalDayWeatherForecast> forcasts = aMapLocalWeatherForecast
					.getWeatherForecast();
			
			
			ArrayList<Weather> weathers = new ArrayList<Weather>();
			
			Intent intent;
			intent = new Intent();
			
			for (int i = 0; i < forcasts.size(); i++) {
				AMapLocalDayWeatherForecast forcast = forcasts.get(i);
				
				Weather weather;
				
				switch (i) {
				// 今天天气
				case 0:
					// 城市
					weather = new Weather();
					weather.setDate(forcast.getDate());
					weather.setDayTemp(forcast.getDayTemp());
					weather.setDayWeather(forcast.getDayWeather());
					weather.setDayWindDir(forcast.getDayWindDir());
					weather.setDayWindPower(forcast.getDayWindPower());
					weather.setNightTemp(forcast.getNightTemp());
					weather.setNightWeather( forcast.getNightWeather());
					weather.setNightWindDir( forcast.getNightWindDir());
					weather.setNightWindPower(forcast.getNightWindPower());
					weather.setWeek(forcast.getWeek());
					
					weathers.add(weather);
					
					
//					weather.put("toDate()", forcast.getDate());  // 日期
//					weather.put("toDayTemp()", forcast.getDayTemp());  // 返回白天天气温度，单位：摄氏度。
//					weather.put("toDayWeather", forcast.getDayWeather());  // 返回白天天气现象，如“晴”、“多云”。
//					weather.put("toDayWindDir", forcast.getDayWindDir());  // 返回白天风向
//					weather.put("toDayWindPower", forcast.getDayWindPower());  //  返回白天风力，单位：级。
//					weather.put("toNightTemp", forcast.getNightTemp());  // 返回夜间天气温度，单位：摄氏度。
//					weather.put("toNightWeather", forcast.getNightWeather());  // 返回夜间天气现象，如“晴”、“多云”。
//					weather.put("toNightWindDir", forcast.getNightWindDir());  //返回夜间风向。
//					weather.put("toNightWindPower", forcast.getNightWindPower());  // 返回夜间风力，单位：级。
//					weather.put("toWeek()", forcast.getWeek());  // 返回白天天气现象，如“晴”、“多云”。
//					weathers.add(weather);
					
					break;
				// 明天天气
				case 1:
					weather = new Weather();
					weather.setDate(forcast.getDate());
					weather.setDayTemp(forcast.getDayTemp());
					weather.setDayWeather(forcast.getDayWeather());
					weather.setDayWindDir(forcast.getDayWindDir());
					weather.setDayWindPower(forcast.getDayWindPower());
					weather.setNightTemp(forcast.getNightTemp());
					weather.setNightWeather( forcast.getNightWeather());
					weather.setNightWindDir( forcast.getNightWindDir());
					weather.setNightWindPower(forcast.getNightWindPower());
					weather.setWeek(forcast.getWeek());
					
					weathers.add(weather);
//					weather = new HashMap<String, String>();
//					weather.put("tomorrowDate()", forcast.getDate());  // 返回白天天气温度，单位：摄氏度。
//					weather.put("tomorrowDayTemp()", forcast.getDayTemp());  // 返回白天天气温度，单位：摄氏度。
//					weather.put("tomorrowDayWeather", forcast.getDayWeather());  // 返回白天天气现象，如“晴”、“多云”。
//					weather.put("tomorrowDayWindDir", forcast.getDayWindDir());  // 返回白天风向
//					weather.put("tomorrowDayWindPower", forcast.getDayWindPower());  //  返回白天风力，单位：级。
//					weather.put("tomorrowNightTemp", forcast.getNightTemp());  // 返回夜间天气温度，单位：摄氏度。
//					weather.put("tomorrowNightWeather", forcast.getNightWeather());  // 返回夜间天气现象，如“晴”、“多云”。
//					weather.put("tomorrowNightWindDir", forcast.getNightWindDir());  //返回夜间风向。
//					weather.put("tomorrowNightWindPower", forcast.getNightWindPower());  // 返回夜间风力，单位：级。
//					weather.put("tomorrowWeek()", forcast.getWeek());  // 返回白天天气现象，如“晴”、“多云”。
//					weathers.add(weather);
					break;
				// 后天天气
				case 2:
					weather = new Weather();
					weather.setDate(forcast.getDate());
					weather.setDayTemp(forcast.getDayTemp());
					weather.setDayWeather(forcast.getDayWeather());
					weather.setDayWindDir(forcast.getDayWindDir());
					weather.setDayWindPower(forcast.getDayWindPower());
					weather.setNightTemp(forcast.getNightTemp());
					weather.setNightWeather( forcast.getNightWeather());
					weather.setNightWindDir( forcast.getNightWindDir());
					weather.setNightWindPower(forcast.getNightWindPower());
					weather.setWeek(forcast.getWeek());
					
					weathers.add(weather);
//					weather = new HashMap<String, String>();
//					weather.put("aftertomorrowDate()", forcast.getDate());  // 返回白天天气温度，单位：摄氏度。
//					weather.put("aftertomorrowDayTemp()", forcast.getDayTemp());  // 返回白天天气温度，单位：摄氏度。
//					weather.put("aftertomorrowDayWeather", forcast.getDayWeather());  // 返回白天天气现象，如“晴”、“多云”。
//					weather.put("aftertomorrowDayWindDir", forcast.getDayWindDir());  // 返回白天风向
//					weather.put("aftertomorrowDayWindPower", forcast.getDayWindPower());  //  返回白天风力，单位：级。
//					weather.put("aftertomorrowNightTemp", forcast.getNightTemp());  // 返回夜间天气温度，单位：摄氏度。
//					weather.put("aftertomorrowNightWeather", forcast.getNightWeather());  // 返回夜间天气现象，如“晴”、“多云”。
//					weather.put("aftertomorrowNightWindDir", forcast.getNightWindDir());  //返回夜间风向。
//					weather.put("aftertomorrowNightWindPower", forcast.getNightWindPower());  // 返回夜间风力，单位：级。
//					weather.put("aftertomorrowWeek()", forcast.getWeek());  // 返回白天天气现象，如“晴”、“多云”。
//					weathers.add(weather);
					break;
				}
			}
			
			Log.i(TAG, "获取天气成功");
			Bundle bundle = new Bundle();  
            bundle.putParcelableArrayList("weather", weathers);
            intent.putExtras(bundle);
			intent.setAction("com.zky.action.weatherForecase");// action与接收器相同
			context.sendBroadcast(intent);
			
			
		} else {
			// 获取天气预报失败
			Toast.makeText(
					context,
					"获取天气预报失败:"
							+ aMapLocalWeatherForecast.getAMapException()
									.getErrorMessage(), Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public void onWeatherLiveSearched(AMapLocalWeatherLive aMapLocalWeatherLive) {
		if (aMapLocalWeatherLive != null
				&& aMapLocalWeatherLive.getAMapException().getErrorCode() == 0) {
			String city = aMapLocalWeatherLive.getCity();// 城市
			String weather = aMapLocalWeatherLive.getWeather();// 天气情况
			String windDir = aMapLocalWeatherLive.getWindDir();// 风向
			String windPower = aMapLocalWeatherLive.getWindPower();// 风力
			String humidity = aMapLocalWeatherLive.getHumidity();// 空气湿度
			String reportTime = aMapLocalWeatherLive.getReportTime();// 数据发布时间
		} else {
			// 获取天气预报失败
			Toast.makeText(
					context,
					"获取天气预报失败:"
							+ aMapLocalWeatherLive.getAMapException()
									.getErrorMessage(), Toast.LENGTH_SHORT)
					.show();
		}
	}

}
