package com.bestplayer098.crashbandicootfanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class CB3Fragment extends ListFragment {

	String Values1[];
	int nposition;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		Values1 = getResources().getStringArray(R.array.Crash_Bandicoot_3);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Values1);
		
		setListAdapter(adapter);
		//return inflater.inflate(R.layout.fragment_cb2, container, false);
		return super.onCreateView(inflater, container, savedInstanceState);
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position1, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position1, id);
		Intent intent= new Intent(getActivity(), MainActivity.class);
		nposition=position1+53;
		intent.putExtra("position1", nposition);
		intent.putExtra("Name1", Values1[position1]);
		if (MainActivity.isInFront==false){
			MainActivity.playMusic.release();
		}
		startActivity(intent);
	}

}
