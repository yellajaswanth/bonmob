package adapter;

import java.util.List;

import model.Order;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.AppController;

import com.android.volley.toolbox.ImageLoader;
import com.jash.bonsoul.R;

public class OrderListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Order> orderItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public OrderListAdapter(Activity activity, List<Order> orderItems) {
		this.activity = activity;
		this.orderItems = orderItems;
	}

	@Override
	public int getCount() {
		return orderItems.size();
	}

	@Override
	public Object getItem(int location) {
		return orderItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.order_item, null);

		TextView orderName = (TextView) convertView
				.findViewById(R.id.orderName);
		TextView itemCost = (TextView) convertView.findViewById(R.id.itemCost);

		Order o = orderItems.get(position);
		orderName.setText(o.getmenuItemName());
		itemCost.setText("Rs. " + o.getcost());

		return convertView;
	}

}