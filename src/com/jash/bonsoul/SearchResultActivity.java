package com.jash.bonsoul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.CustomListAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import model.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import app.AppController;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

public class SearchResultActivity extends Activity {

	// Log tag
	private static final String TAG = SearchResultActivity.class.getSimpleName();

	// Movies json url
	private static final String url = "http://bonsoul.com/fetch_venue_result.php?location=";
	private ProgressDialog pDialog;
	private List<Venue> movieList = new ArrayList<Venue>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		this.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_left);
		listView = (ListView) findViewById(R.id.list);		
		adapter = new CustomListAdapter(SearchResultActivity.this, movieList);
		listView.setAdapter(adapter);
		
		 pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
	 
		// changing action bar color
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));

		LoadAllProduct();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void LoadAllProduct(){
		
			JSONArray venues = null;
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("location", "Hyderabad");
			
			
			JsonArrayRequest movieReq = new JsonArrayRequest(url+"hyderabad",
				       new Response.Listener<JSONArray>() {
				           @Override
				           public void onResponse(JSONArray response) {
				               try {
				                   VolleyLog.v("Response:%n %s", response.toString(4));
				                   hidePDialog();
				                   Log.d(TAG, response.toString());
				                // Parsing json
			                        for (int i = 0; i < response.length(); i++) {
			                            try {
			 
			                                JSONObject obj = response.getJSONObject(i);
			                                Venue venue = new Venue();
			                                venue.setVenueName(obj.getString("venuename"));			                                
			                                venue.setRating(obj.getString("venuerating"));
			                                venue.setReviewCount(obj.getString("reviewcount"));		 
			                                
			 
			                                // adding movie to movies array
			                                movieList.add(venue);
			 
			                            } catch (JSONException e) {
			                                e.printStackTrace();
			                            }
			 
			                        }
				                
			                        adapter.notifyDataSetChanged();
				               } catch (JSONException e) {
				                   e.printStackTrace();
				               }
				           }
				       }, new Response.ErrorListener() {
				           @Override
				           public void onErrorResponse(VolleyError error) {
				               VolleyLog.e("Error: ", error.getMessage());
				               hidePDialog();
				           }
				       });
	     
			// Adding request to request queue
			AppController.getInstance().addToRequestQueue(movieReq);
			
		}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			LoadAllProduct();
		}
		return super.onOptionsItemSelected(item);
	}

	
}