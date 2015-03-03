package model;

import java.util.ArrayList;

public class Venue {
	private String venueName, thumbnailUrl;
	private String reviewCount;
	private String rating;
	private ArrayList<String> genre;

	public Venue() {
	}

	public Venue(String name,  String reviewCount, String rating,
			ArrayList<String> genre) {
		this.venueName = name;
//		this.thumbnailUrl = thumbnailUrl;
		this.reviewCount = reviewCount;
		this.rating = rating;
		this.genre = genre;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String name) {
		this.venueName = name;
	}

//	public String getThumbnailUrl() {
//		return thumbnailUrl;
//	}
//
//	public void setThumbnailUrl(String thumbnailUrl) {
//		this.thumbnailUrl = thumbnailUrl;
//	}

	public String getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(String rc) {
		this.reviewCount = rc;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

}
