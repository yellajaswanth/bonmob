package adapter;

import java.util.List;

import model.Venue;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.AppController;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jash.bonsoul.R;

public class SearchListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Venue> venueItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public SearchListAdapter(Activity activity, List<Venue> venueItems) {
		this.activity = activity;
		this.venueItems = venueItems;
	}

	@Override
	public int getCount() {
		return venueItems.size();
	}

	@Override
	public Object getItem(int location) {
		return venueItems.get(location);
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
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.venueSearchImg);
		TextView venueName = (TextView) convertView
				.findViewById(R.id.venueName);
		TextView venueLocation = (TextView) convertView
				.findViewById(R.id.venueLocation);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);

		// getting movie data for the row
		Venue m = venueItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getPhoto(), imageLoader);

		// VenueName
		venueName.setText(m.getVenueName());

		// VenueLocation
		venueLocation.setText(m.getVenueLocation());

		// VenueRating
		rating.setText(m.getRating());

		return convertView;
	}
}