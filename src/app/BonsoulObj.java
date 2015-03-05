package app;

public class BonsoulObj {
	private String venueId;

	static final private BonsoulObj INSTANCE = new BonsoulObj("0");

	static public BonsoulObj getInstance() {
		return INSTANCE;
	}

	public BonsoulObj(String venueId) {
		this.venueId = venueId;
	}

	// Getters & Setters
	public String getVenueID() {
		return venueId;
	}

	public void setVenueID(String vid) {
		this.venueId = vid;
	}

}
