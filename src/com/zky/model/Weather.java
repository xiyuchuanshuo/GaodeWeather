package com.zky.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable {
	private String date; // 日期
	private String dayTemp; // 返回白天天气温度，单位：摄氏度。
	private String dayWeather; // 返回白天天气现象，如“晴”、“多云”。
	private String dayWindDir; // 返回白天风向
	private String dayWindPower;// 返回白天风力，单位：级。
	private String nightTemp; // 返回夜间天气温度，单位：摄氏度。
	private String nightWeather;// 返回夜间天气现象，如“晴”、“多云”。
	private String nightWindDir;// 返回夜间风向。
	private String nightWindPower;// 返回夜间风力，单位：级。
	private String week; // 返回预报天气的星期。

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayTemp() {
		return dayTemp;
	}

	public void setDayTemp(String dayTemp) {
		this.dayTemp = dayTemp;
	}

	public String getDayWeather() {
		return dayWeather;
	}

	public void setDayWeather(String dayWeather) {
		this.dayWeather = dayWeather;
	}

	public String getDayWindDir() {
		return dayWindDir;
	}

	public void setDayWindDir(String dayWindDir) {
		this.dayWindDir = dayWindDir;
	}

	public String getDayWindPower() {
		return dayWindPower;
	}

	public void setDayWindPower(String dayWindPower) {
		this.dayWindPower = dayWindPower;
	}

	public String getNightTemp() {
		return nightTemp;
	}

	public void setNightTemp(String nightTemp) {
		this.nightTemp = nightTemp;
	}

	public String getNightWeather() {
		return nightWeather;
	}

	public void setNightWeather(String nightWeather) {
		this.nightWeather = nightWeather;
	}

	public String getNightWindDir() {
		return nightWindDir;
	}

	public void setNightWindDir(String nightWindDir) {
		this.nightWindDir = nightWindDir;
	}

	public String getNightWindPower() {
		return nightWindPower;
	}

	public void setNightWindPower(String nightWindPower) {
		this.nightWindPower = nightWindPower;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		// TODO Auto-generated method stub

		parcel.writeString(date);
		parcel.writeString(dayTemp); // 返回白天天气温度，单位：摄氏度。
		parcel.writeString(dayWeather); // 返回白天天气现象，如“晴”、“多云”。
		parcel.writeString(dayWindDir); // 返回白天风向
		parcel.writeString(dayWindPower);// 返回白天风力，单位：级。
		parcel.writeString(nightTemp); // 返回夜间天气温度，单位：摄氏度。
		parcel.writeString(nightWeather);// 返回夜间天气现象，如“晴”、“多云”。
		parcel.writeString(nightWindDir);// 返回夜间风向。
		parcel.writeString(nightWindPower);// 返回夜间风力，单位：级。
		parcel.writeString(week); // 返回预报天气的星期。
	}

	public static final Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {
		@Override
		public Weather[] newArray(int size) {
			return new Weather[size];
		}

		@Override
		public Weather createFromParcel(Parcel in) {
			return new Weather(in);
		}
	};

	public Weather(Parcel in) {
		date = in.readString();
		dayTemp = in.readString(); // 返回白天天气温度，单位：摄氏度。
		dayWeather = in.readString(); // 返回白天天气现象，如“晴”、“多云”。
		dayWindDir = in.readString(); // 返回白天风向
		dayWindPower = in.readString();// 返回白天风力，单位：级。
		nightTemp = in.readString(); // 返回夜间天气温度，单位：摄氏度。
		nightWeather = in.readString();// 返回夜间天气现象，如“晴”、“多云”。
		nightWindDir = in.readString();// 返回夜间风向。
		nightWindPower = in.readString();// 返回夜间风力，单位：级。
		week = in.readString(); // 返回预报天气的星期。
	}

	public Weather() {
		super();
	}

	
}
