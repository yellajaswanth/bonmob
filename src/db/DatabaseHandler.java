package db;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "bonsoulDB";
	private static final int DATABASE_VERSION = 1;

	// Table Name
	private static final String TABLE_ORDER = "order";
	// Order Table Columns
	private static final String KEY_ID = "id";
	private static final String KEY_VENUEID = "venueId";
	private static final String KEY_MENUITEMID = "menuItemId";
	private static final String KEY_MENUCATEGORYID = "menuCategoryId";
	private static final String KEY_COST = "cost";
	private static final String KEY_DISCOUNT = "discount";
	private static final String KEY_MENUITEMNAME = "menuItemName";
	private static final String KEY_MENUCATEGORYNAME = "menuCategoryName";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_VENUEID + " INTEGER,"
				+ KEY_MENUITEMID + " INTEGER," + KEY_MENUCATEGORYID
				+ " INTEGER," + KEY_COST + " INTEGER," + KEY_DISCOUNT
				+ " INTEGER," + KEY_MENUITEMNAME + " TEXT,"
				+ KEY_MENUCATEGORYNAME + " TEXT";
		db.execSQL(CREATE_ORDER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Add Order to the db.

	void addOrder(Order order) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, order.getId());
		values.put(KEY_VENUEID, order.getVenueId());
		values.put(KEY_MENUITEMID, order.getmenuItemId());
		values.put(KEY_MENUCATEGORYID, order.getmenuCategoryId());
		values.put(KEY_COST, order.getcost());
		values.put(KEY_DISCOUNT, order.getdiscount());
		values.put(KEY_MENUITEMNAME, order.getmenuItemName());
		values.put(KEY_MENUCATEGORYNAME, order.getMenuCategoryName());

		db.insert(TABLE_ORDER, null, values);
		db.close();
	}

	// Getting All Orders
	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<Order>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDER;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Order order = new Order();
				order.setId(Integer.parseInt(cursor.getString(0)));
				order.setVenueId(Integer.parseInt(cursor.getString(1)));
				order.setMenuItemId(Integer.parseInt(cursor.getString(2)));
				order.setMenuCategoryId(Integer.parseInt(cursor.getString(3)));
				order.setCost(Integer.parseInt(cursor.getString(4)));
				order.setDiscount(Integer.parseInt(cursor.getString(5)));
				order.setMenuItemName(cursor.getString(6));
				order.setMenuCategoryName(cursor.getString(7));

				// Adding order to list
				orderList.add(order);
			} while (cursor.moveToNext());
		}

		// return order list
		return orderList;
	}

	// Deleting single contact
	public void deleteOrder(Order contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ORDER, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getId()) });
		db.close();
	}

	// Getting contacts Count
	public int getOrdersCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ORDER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
}
