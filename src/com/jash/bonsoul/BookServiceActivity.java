package com.jash.bonsoul;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import adapter.OrderListAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class BookServiceActivity extends Activity {

	private ProgressDialog pDialog;
	private List<Order> orderList = new ArrayList<Order>();
	private ListView listView;
	private OrderListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_service);
		this.overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_left);
		listView = (ListView) findViewById(R.id.orderitemslv);
		adapter = new OrderListAdapter(BookServiceActivity.this, orderList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		LoadAllProduct();
	}

	private void LoadAllProduct() {
		Order order;
		order = new Order();
		order.setMenuItemName("Hair - Trimming Men");
		order.setCost(500);
		orderList.add(order);

		order = new Order();
		order.setMenuItemName("Shave / Trimming");
		order.setCost(500);
		orderList.add(order);

		hidePDialog();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
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

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}
}
