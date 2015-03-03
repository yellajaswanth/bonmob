package com.jash.bonsoul;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ServicesActivity extends Activity {
	
	private ListView servicesList;
	
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://api.androidhive.info/contacts/";
	
	private final String CLASS_TAG = "ServiceActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services);
		
		this.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_left);
		
		 String[] values = new String[] { 
				 "Haircuts and HairDressing",
				 "Blow Dry",
				 "Hair Consulting",
				 "Hair Transplants",
				 "Hair Extensions",
				 "Straighteners"
             };
		 
		 servicesList = (ListView) findViewById(R.id.services_list);
		 
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
		 
		 servicesList.setAdapter(adapter);
		 
		 servicesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent searchResults = new Intent(ServicesActivity.this, SearchResultActivity.class);
				startActivity(searchResults);
				
				Log.d(CLASS_TAG, "position:" + position + ", id:" + id);
				
			}
			 
		});
		 
		 
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.services, menu);
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
}
