package com.jash.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jash.bonsoul.MainActivity;
import com.jash.bonsoul.R;
import com.jash.bonsoul.ServicesActivity;

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
	public class HomeFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		
		/**
		 * Variables
		 *
		 */
		
		private ImageView iv1, iv2, iv3, iv4, iv5, iv6;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static HomeFragment newInstance(int sectionNumber) {
			HomeFragment fragment = new HomeFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public HomeFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			iv1 = (ImageView) rootView.findViewById(R.id.imageView1);
			iv1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent serviceIntent = new Intent(getActivity(), ServicesActivity.class);
					startActivity(serviceIntent);					
				}
			});
			
			return rootView;
		}
	

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}