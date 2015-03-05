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
	private List<Venue> movieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public SearchListAdapter(Activity activity, List<Venue> movieItems) {
		this.activity = activity;
		this.movieItems = movieItems;
	}

	@Override
	public int getCount() {
		return movieItems.size();
	}

	@Override
	public Object getItem(int location) {
		return movieItems.get(location);
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
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);
		TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Venue m = movieItems.get(position);

		// thumbnail image
//		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// title
		title.setText(m.getVenueName());
		
		// rating
		rating.setText("Rating: " + String.valueOf(m.getRating()));
		
//		// genre
//		String genreStr = "";
//		for (String str : m.getGenre()) {
//			genreStr += str + ", ";
//		}
//		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//				genreStr.length() - 2) : genreStr;
//		genre.setText(genreStr);
		
		// release year
		year.setText(String.valueOf(m.getReviewCount()) + " reviews");

		return convertView;
	}

}