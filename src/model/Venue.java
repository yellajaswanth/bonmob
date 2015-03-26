package model;

public class Venue {
	private String venueName;
	private String venueLocation;
	private String rating;
	private int venueId;
	private String contact;
	private String photo;

	public Venue() {
	}

	public Venue(String name, String venueLocation, String rating,
			String contact, int venueId, String photo) {
		this.venueName = name;
		// this.thumbnailUrl = thumbnailUrl;
		this.venueLocation = venueLocation;
		this.rating = rating;
		this.contact = contact;
		this.venueId = venueId;
		this.photo = photo;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String name) {
		this.venueName = name;
	}

	public int getVenueID() {
		return venueId;
	}

	public void setVenueID(int vId) {
		this.venueId = vId;
	}

	public String getVenueLocation() {
		return venueLocation;
	}

	public void setVenueLocation(String vl) {
		this.venueLocation = vl;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photoURL) {
		this.photo = photoURL;
	}

}
