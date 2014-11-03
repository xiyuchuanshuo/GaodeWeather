package com.zky.activity;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.nineoldandroids.view.ViewHelper;
import com.zky.fragment.BaiduMapFragment;
import com.zky.fragment.EmergencyFragment;
import com.zky.fragment.NewsFragment;
import com.zky.fragment.VoiceFragment;
import com.zky.utils.WeatherSearch;
import com.zky.view.CircularImage;
import com.zky.view.DragLayout;
import com.zky.view.DragLayout.DragListener;
public class MainActivity extends FragmentActivity implements OnClickListener,
		AMapLocationListener {

	// 加载各个模块
	@SuppressWarnings("rawtypes")
	public static final Class[] fragments = { VoiceFragment.class,
			NewsFragment.class, BaiduMapFragment.class, EmergencyFragment.class };
	public static FragmentTabHost mTabHost;
	public static RadioGroup mTabRg;

	private RadioButton mRadioButton1;
//	private RadioButton mRadioButton2;
//	private RadioButton mRadioButton3;
//	private RadioButton mRadioButton4;

	public static DragLayout dl;
	private TextView exit_txt;

	private boolean isOpen = false;
	WeatherSearch weatherSearch; 
	WeatherSearch search;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setOverflowShowingAlways();
		ActionBar actionBar = getActionBar();
		View actionbarLayout = LayoutInflater.from(this).inflate(
				R.layout.actionbar_main, null);
		CircularImage circularImage = (CircularImage) actionbarLayout
				.findViewById(R.id.actionbar_img);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setCustomView(actionbarLayout);
		setContentView(R.layout.activity_main);
		
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setDragListener(new DragListener() {
			@Override
			public void onOpen() {
				// lv.smoothScrollToPosition(new Random().nextInt(30));
				// circularImage.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onClose() {
			}

			@Override
			public void onDrag(float percent) {
				animate(percent);
			}
		});

		initTheSideView();
		initView();
		circularImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isOpen) {
					dl.close();
					isOpen = false;
				} else {
					dl.open();
					isOpen = true;
				}
			}
		});
		
		
		
		
		
		
//		init();
//		 Intent intent = new Intent("com.zky.server.MapServer");
//		  startService(intent); 
		
	}

	// 菜单键消失
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private LocationManagerProxy mLocationManagerProxy;

	private void init() {

		mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, -1, 15, this);
		mLocationManagerProxy.setGpsEnable(false);
	}

	public void initTheSideView() {
		exit_txt = (TextView) findViewById(R.id.exit_txt);
		exit_txt.setOnClickListener(this);
	}

	private void animate(float percent) {
		ViewGroup vg_left = dl.getVg_left();
		ViewGroup vg_main = dl.getVg_main();

		float f1 = 1 - percent * 0.3f;
		ViewHelper.setScaleX(vg_main, f1);
		ViewHelper.setScaleY(vg_main, f1);
		ViewHelper.setTranslationX(vg_left, -vg_left.getWidth() / 2.2f
				+ vg_left.getWidth() / 2.2f * percent);
		ViewHelper.setScaleX(vg_left, 0.5f + 0.5f * percent);
		ViewHelper.setScaleY(vg_left, 0.5f + 0.5f * percent);
		ViewHelper.setAlpha(vg_left, percent);

		int color = (Integer) evaluate(percent, Color.parseColor("#ff000000"),
				Color.parseColor("#00000000"));
	}

	// 初始化fragment对象
	private void initView() {
		// TODO Auto-generated method stub
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(MainActivity.this, getSupportFragmentManager(),
				R.id.realtabcontent);
		// 得到fragment的个数
		int count = fragments.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragments[i], null);
		}

		mTabRg = (RadioGroup) findViewById(R.id.tab_rg_menu);
		mRadioButton1 = (RadioButton) findViewById(R.id.tab_rb_1);
//		mRadioButton2 = (RadioButton) findViewById(R.id.tab_rb_2);
//		mRadioButton3 = (RadioButton) findViewById(R.id.tab_rb_3);
//		mRadioButton4 = (RadioButton) findViewById(R.id.tab_rb_4);
		mRadioButton1.setChecked(true);
		mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tab_rb_1:
					mTabHost.setCurrentTab(0);
					break;
				case R.id.tab_rb_2:
					mTabHost.setCurrentTab(1);

					break;
				case R.id.tab_rb_3:
					mTabHost.setCurrentTab(2);
					break;
				case R.id.tab_rb_4:
					mTabHost.setCurrentTab(3);
					break;
				default:
					break;
				}
			}
		});

		mTabHost.setCurrentTab(0);

	}

	/**
	 * 菜单、返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(this); // 调用双击退出函数
		}
		return false;
	}

	/**
	 * 
	 * @param activity
	 */
	private static Boolean isExit = false;

	public static void exitBy2Click(Context context) {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			System.exit(0);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.exit_txt:
			finish();
			break;

		default:
			break;
		}
	}

	public static Object evaluate(float fraction, Object startValue,
			Object endValue) {
		int startInt = (Integer) startValue;
		int startA = (startInt >> 24) & 0xff;
		int startR = (startInt >> 16) & 0xff;
		int startG = (startInt >> 8) & 0xff;
		int startB = startInt & 0xff;
		int endInt = (Integer) endValue;
		int endA = (endInt >> 24) & 0xff;
		int endR = (endInt >> 16) & 0xff;
		int endG = (endInt >> 8) & 0xff;
		int endB = endInt & 0xff;
		return (int) ((startA + (int) (fraction * (endA - startA))) << 24)
				| (int) ((startR + (int) (fraction * (endR - startR))) << 16)
				| (int) ((startG + (int) (fraction * (endG - startG))) << 8)
				| (int) ((startB + (int) (fraction * (endB - startB))));
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	private CharSequence mTitle;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Only show items in the action bar relevant to this screen
		// if the drawer is not showing. Otherwise, let the drawer
		// decide what to show in the action bar.
		getMenuInflater().inflate(R.menu.main, menu);
		restoreActionBar();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
		}

	}

}
