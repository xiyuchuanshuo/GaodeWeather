package com.zky.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zky.activity.R;
import com.zky.adapter.WeatherAdapter;
import com.zky.broadcastreceiver.WeatherReceiver;
import com.zky.data.ConstantData;
import com.zky.model.Weather;
import com.zky.utils.WeatherSearch;

public class VoiceFragment extends Fragment {

	WeatherReceiver receiver;
	ListView ll;
	SharedPreferences sharedPreferences;
	WeatherAdapter weatherAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View parentView = inflater
				.inflate(R.layout.fragment2, container, false);

		ll = (ListView) parentView.findViewById(R.id.myll);

		sharedPreferences = getActivity().getSharedPreferences(ConstantData.SHAREPERFERSNAME, Context.MODE_PRIVATE);
		int ddd=sharedPreferences.getInt("i",0);
		String str=sharedPreferences.getString("str","");
		
		
		List<Map<String, Integer>> s = new ArrayList<Map<String, Integer>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("name", i);
			s.add(m);
			
		}
		
		ArrayList<Weather> weathers = new ArrayList<Weather>();
		for (int i = 0; i < 3; i++) {
			Weather weather = new Weather();
			weather.setDayTemp(i+"");
			weather.setNightTemp(i+"");
			weathers.add(weather);
		}
		
		new WeatherSearch(getActivity());
		
		
		weatherAdapter = new WeatherAdapter(getActivity(),weathers );
//		SimpleAdapter adapter = new SimpleAdapter(getActivity(),s,
//				android.R.layout.simple_list_item_1, new String[] { "name"+""},
//						new int[] { android.R.id.text1 } );

		ll.setAdapter(weatherAdapter);
		
		
		receiver = new WeatherReceiver(weatherAdapter, weathers);
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.zky.action.weatherForecase");
		filter.addAction("com.zky.action.weatherLive");
		getActivity().registerReceiver(receiver, filter);

		
		
		
		Button b = (Button)parentView.findViewById(R.id.mybt);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intent;
//				intent = new Intent();
//				intent.setAction("com.zky.action.weatherForecase");// action与接收器相同
//				getActivity().sendBroadcast(intent);
				new WeatherSearch(getActivity());
			}
		});
		
		
		return parentView;

	}

}
