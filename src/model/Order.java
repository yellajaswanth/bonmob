package model;

public class Order {

	int id;
	int venueId;
	int menuItemId;
	int menuCategoryId;
	int cost;
	int discount;

	String menuItemName;
	String menuCategoryName;

	// Constructor
	public Order() {
	}

	public Order(int id, int venueId, int menuItemId, int menuCategoryId,
			int cost, int discount, String menuItemName, String menuCategoryName) {
		this.id = id;
		this.venueId = venueId;
		this.menuItemId = menuItemId;
		this.menuCategoryId = menuCategoryId;
		this.cost = cost;
		this.discount = discount;
		this.menuItemName = menuItemName;
		this.menuCategoryName = menuCategoryName;

	}

	public Order(int venueId, int menuItemId, int menuCategoryId, int cost,
			int discount, String menuItemName, String menuCategoryName) {
		this.venueId = venueId;
		this.menuItemId = menuItemId;
		this.menuCategoryId = menuCategoryId;
		this.cost = cost;
		this.discount = discount;
		this.menuItemName = menuItemName;
		this.menuCategoryName = menuCategoryName;
	}

	public int getId() {
		return id;
	}

	public int getVenueId() {
		return venueId;
	}

	public int getmenuItemId() {
		return menuItemId;
	}

	public int getmenuCategoryId() {
		return menuCategoryId;
	}

	public int getcost() {
		return cost;
	}

	public int getdiscount() {
		return discount;
	}

	public String getmenuItemName() {
		return menuItemName;
	}

	public String getMenuCategoryName() {
		return menuCategoryName;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	public void setVenueId(int _vid) {
		this.venueId = _vid;
	}

	public void setMenuItemId(int _mid) {
		this.menuItemId = _mid;
	}

	public void setMenuCategoryId(int _mci) {
		this.menuCategoryId = _mci;
	}

	public void setCost(int _cost) {
		this.cost = _cost;
	}

	public void setDiscount(int _disc) {
		this.discount = _disc;
	}

	public void setMenuItemName(String mn) {
		this.menuItemName = mn;
	}

	public void setMenuCategoryName(String mcn) {
		this.menuCategoryName = mcn;
	}
}
