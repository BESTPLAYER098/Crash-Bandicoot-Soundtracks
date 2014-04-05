package com.bestplayer098.crashbandicootfanapp;

import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class Select2Activity extends FragmentActivity {
	
	ViewPager viewPager=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select2);
		
		viewPager=(ViewPager) findViewById(R.id.pager);
		FragmentManager fragmentManager=getSupportFragmentManager();
		viewPager.setAdapter(new MyAdapter(fragmentManager, getBaseContext()));
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		MainActivity.nm.cancel(MainActivity.uniqueID);
	}

}

class MyAdapter extends FragmentPagerAdapter {
	private Context mContext = null;
	
	public MyAdapter(FragmentManager fm, Context context) {
		super(fm);
		mContext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position==0){
			fragment=new CB1Fragment();
		}
		if (position==1){
			fragment=new CB2Fragment();
		}
		if (position==2){
			fragment=new CB3Fragment();
		}
		if (position==3){
			fragment=new CTRFragment();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.cb1).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.cb2).toUpperCase(l);
		case 2:
			return mContext.getString(R.string.cb3).toUpperCase(l);
		case 3:
			return mContext.getString(R.string.ctr).toUpperCase(l);
		}
		return null;
	}
	
}
