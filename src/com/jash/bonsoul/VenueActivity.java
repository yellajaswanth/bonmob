package com.jash.bonsoul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.ExpandableListAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import app.AppController;
import app.BonsoulObj;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

public class VenueActivity extends Activity {

	private TextView tv1;
	private final String TAG = "VenueActivity";
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	private ProgressDialog pDialog;
	private TextView menuItemTv1, menuItemTv2;

	private ScrollView venueScrollview;

	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	private String finalURL = "";

	private TextView venuename, venueopen, venueaddress, venueReviewCount;

	private static final String url = "http://bonsoul.com/get_venue_details.php?venueid=";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_venue);
		this.overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_left);

		venuename = (TextView) findViewById(R.id.venuename);
		venueopen = (TextView) findViewById(R.id.venueopen);
		venueaddress = (TextView) findViewById(R.id.venueaddress);
		venueReviewCount = (TextView) findViewById(R.id.venueReviewCount);
		venueScrollview = (ScrollView) findViewById(R.id.venueScrollview);
		venueScrollview.scrollTo(0, 0);

		tv1 = (TextView) findViewById(R.id.venuename);

		finalURL = url + BonsoulObj.getInstance().getVenueID();
		LoadAllProduct();

	}

	@Override
	protected void onResume() {
		super.onResume();

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		menuItemTv1 = (TextView) findViewById(R.id.lblListItem);
		menuItemTv2 = (TextView) findViewById(R.id.cost);
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		try {
			setListViewHeight(expListView);
		} catch (Exception ex) {
			Log.d(TAG, ex.toString());
		}

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				try {
					setListViewHeight(parent, groupPosition);
				} catch (Exception e) {
					Log.d(TAG, e.toString());
				}
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// Toast.makeText(getApplicationContext(),
				// listDataHeader.get(groupPosition) + " Collapsed",
				// Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// Toast.makeText(
				// getApplicationContext(),
				// listDataHeader.get(groupPosition)
				// + " : "
				// + listDataChild.get(
				// listDataHeader.get(groupPosition)).get(
				// childPosition), Toast.LENGTH_SHORT)
				// .show();
				Intent i = new Intent(VenueActivity.this,
						BookServiceActivity.class);

				startActivity(i);
				return false;

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.venue, menu);
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

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Hair");
		listDataHeader.add("Body");
		listDataHeader.add("Face");
		listDataHeader.add("Nails");
		listDataHeader.add("Massage");

		// Adding child data
		List<String> hair = new ArrayList<String>();
		hair.add("Hair Cut - Kids");
		hair.add("Hair Trimming - Women");
		hair.add("Hair - Trimming - Men");
		hair.add("Shave / Trimming");
		hair.add("Hair Trimming with Hair Wash - Men");

		List<String> body = new ArrayList<String>();
		body.add("African Coco Butter Moisturizing Scrub - 30 Mins");
		body.add("Australian Tea Tree Oil Exfoliating Scrub - 30 Mins");
		body.add("Almond Honey Cleanser Scrub - 30 Mins");
		body.add("Whitening Scrub - 30 Mins");
		body.add("Coco Butter Deep Moisturizing Wrap - 30 Mins");
		body.add("Natural Fruit Wrap - 30 Mins");

		List<String> face = new ArrayList<String>();
		face.add("Antiaging Facial - 60 Mins");
		face.add("O2 Signature Facial - 90 Mins");
		face.add("White & Bright Facial - 60 Mins");
		face.add("Deep Cleansing Facial - 30 Mins");
		face.add("Organic Facial - 60 Mins");

		List<String> nails = new ArrayList<String>();
		nails.add("French Pedicure - 40 Mins");
		nails.add("Spa Pedicure - 30 Mins");
		nails.add("French Manicure - 40 Mins");
		nails.add("Spa Manicure - 30 Mins");

		List<String> massage = new ArrayList<String>();
		massage.add("BaliBliss Massage - 60 Mins");
		massage.add("Rejuvenate - 90 Mins");
		massage.add("Relaxation - 120 Mins");
		massage.add("Foot Reflexology - 60 Mins");

		listDataChild.put(listDataHeader.get(0), hair); // Header, Child data
		listDataChild.put(listDataHeader.get(1), body);
		listDataChild.put(listDataHeader.get(2), face);
		listDataChild.put(listDataHeader.get(3), nails);
		listDataChild.put(listDataHeader.get(4), massage);
	}

	private void setListViewHeight(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	private void setListViewHeight(ExpandableListView listView, int group) {
		android.widget.ExpandableListAdapter listAdapter = listView
				.getExpandableListAdapter();
		int totalHeight = 0;
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
				MeasureSpec.AT_MOST);
		for (int i = 0; i < listAdapter.getGroupCount(); i++) {
			View groupItem = listAdapter.getGroupView(i, false, null, listView);
			groupItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

			totalHeight += groupItem.getMeasuredHeight();

			if (((listView.isGroupExpanded(i)) && (i != group))
					|| ((!listView.isGroupExpanded(i)) && (i == group))) {
				for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
					View listItem = listAdapter.getChildView(i, j, false, null,
							listView);
					listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);

					totalHeight += listItem.getMeasuredHeight();

				}
			}
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		int height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
		if (height < 10)
			height = 200;
		params.height = height;
		listView.setLayoutParams(params);
		listView.requestLayout();

	}

	public void LoadAllProduct() {

		// Adding request to request queue
		Log.d(TAG, finalURL);
		JsonObjectRequest venueReq = new JsonObjectRequest(Method.GET,
				finalURL, null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {

							if (imageLoader == null)
								imageLoader = AppController.getInstance()
										.getImageLoader();
							NetworkImageView coverImg = (NetworkImageView) findViewById(R.id.venueMainImage);
							NetworkImageView thumb1 = (NetworkImageView) findViewById(R.id.thumb1);
							NetworkImageView thumb2 = (NetworkImageView) findViewById(R.id.thumb2);
							NetworkImageView thumb3 = (NetworkImageView) findViewById(R.id.thumb3);
							NetworkImageView thumb4 = (NetworkImageView) findViewById(R.id.thumb4);
							// Parsing json object response
							// response will be a json object
							JSONArray venueJSONArray = response
									.getJSONArray("venueInfo");
							JSONArray reviewJSONArray = response
									.getJSONArray("reviewInfo");
							JSONArray venueJSON = venueJSONArray
									.getJSONArray(0);
							JSONArray reviewJSON = reviewJSONArray
									.getJSONArray(0);

							for (int i = 0; i < venueJSON.length(); i++) {

								try {
									JSONObject obj = venueJSON.getJSONObject(i);
									venuename.setText(obj.getString("name"));
									Log.d(TAG, obj.getString("name"));
									venueaddress.setText(obj
											.getString("address"));
									Log.d(TAG, obj.getString("address"));
									venueReviewCount.setText(String
											.valueOf(reviewJSON.length())
											+ " reviews");

									JSONArray photosArray = obj
											.getJSONArray("photos");
									for (int j = 0; j < photosArray.length(); j++) {
										JSONObject imgObj = photosArray
												.getJSONObject(j);
										if (j == 0) {
											coverImg.setImageUrl(
													"http://d2rmoau0tbh3pz.cloudfront.net/"
															+ imgObj.get("original"),
													imageLoader);
										} else if (j == 1) {
											thumb1.setImageUrl(
													"http://d2rmoau0tbh3pz.cloudfront.net/"
															+ imgObj.get("original"),
													imageLoader);
										} else if (j == 2) {
											thumb2.setImageUrl(
													"http://d2rmoau0tbh3pz.cloudfront.net/"
															+ imgObj.get("original"),
													imageLoader);
										} else if (j == 3) {
											thumb3.setImageUrl(
													"http://d2rmoau0tbh3pz.cloudfront.net/"
															+ imgObj.get("original"),
													imageLoader);
										} else if (j == 4) {
											thumb4.setImageUrl(
													"http://d2rmoau0tbh3pz.cloudfront.net/"
															+ imgObj.get("original"),
													imageLoader);
										}
									}

								} catch (Exception ex) {
								}

							}

						} catch (JSONException e) {
							e.printStackTrace();
							Log.d(TAG, e.toString());
						}
						hidePDialog();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(),
								error.getMessage(), Toast.LENGTH_SHORT).show();
						// hide the progress dialog
						hidePDialog();
					}
				});
		AppController.getInstance().addToRequestQueue(venueReq);

	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

}
