package com.jash.bonsoul;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ServicesActivity extends Activity {

	private ListView servicesList;

	private ProgressDialog pDialog;

	private EditText searchservice_input;

	ArrayAdapter<String> adapter;

	// URL to get contacts JSON
	private static String url = "http://api.androidhive.info/contacts/";

	private final String CLASS_TAG = "ServiceActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services);

		this.overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_left);

		String[] values = new String[] { "Haircuts and HairDressing",
				"Blow Dry", "Hair Consulting", "Hair Transplants",
				"Hair Extensions", "Straighteners" };

		servicesList = (ListView) findViewById(R.id.services_list);

		searchservice_input = (EditText) findViewById(R.id.searchservices_input);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		servicesList.setAdapter(adapter);

		servicesList.setTextFilterEnabled(true);

		servicesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Intent searchResults = new Intent(ServicesActivity.this,
				// SearchResultActivity.class);
				// startActivity(searchResults);

				Log.d(CLASS_TAG, "position:" + position + ", id:" + id);

			}

		});

		/**
		 * Enabling Search Filter
		 * */
		searchservice_input.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				ServicesActivity.this.adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
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
