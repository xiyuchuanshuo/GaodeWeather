package com.zky.adapter;

import java.util.ArrayList;

import com.zky.activity.R;
import com.zky.model.Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeatherAdapter extends BaseAdapter{

	private Context context;
	ArrayList<Weather> weathers;
	public WeatherAdapter(Context context,ArrayList<Weather> weathers) {
		super();
		this.context = context;
		this.weathers = weathers;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		  return weathers.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		 ViewHolder holder = null;
         //如果缓存convertView为空，则需要创建View
         if(convertView == null)
         {
             holder = new ViewHolder();
             //根据自定义的Item布局加载布局
             convertView = LayoutInflater.from(context).inflate(R.layout.weather_item, null);
             holder.dayTemp = (TextView)convertView.findViewById(R.id.weather_day_temp);
             holder.nightTemp = (TextView)convertView.findViewById(R.id.weather_night_temp);
             //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
             convertView.setTag(holder);
         }else
         {
             holder = (ViewHolder)convertView.getTag();
         }
         holder.dayTemp.setText(weathers.get(position).getDayWeather());
         holder.nightTemp.setText(weathers.get(position).getNightTemp());
         
         return convertView;
	}
	
	class ViewHolder
	    {
	        public TextView dayTemp;
	        public TextView nightTemp;
	    }

	
	
	
	
	
	
	
	
	
}
