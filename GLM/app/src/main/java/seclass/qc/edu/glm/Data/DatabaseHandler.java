package seclass.qc.edu.glm.Data;

import seclass.qc.edu.glm.Model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "groceryListManager";

    // Table Names
    private static final String TABLE_ITEM = "grocery_item_table";
    private static final String TABLE_LIST = "grocery_list_table";
    private static final String TABLE_ITEM_LIST = "item_list_table";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // Items Table - column nmaes
    private static final String KEY_ITEM_NAME = "item";
    private static final String KEY_QUANTITY = "quantity";

    // List Table - column names
    private static final String KEY_LIST_NAME = "grocery_list";

    // Item_List Table - column names
    private static final String KEY_ITEM_ID = "item_id";
    private static final String KEY_LIST_ID = "list_id";

    // Table Create Statements
    // GroceryItem table create statement
    private static final String CREATE_TABLE_GROCERYITEM = "CREATE TABLE "
            + TABLE_ITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM_NAME
            + " TEXT," + KEY_QUANTITY + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // GroceryList table create statement
    private static final String CREATE_TABLE_GROCERY_LIST = "CREATE TABLE " + TABLE_LIST
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LIST_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // ListItem table create statement
    private static final String CREATE_TABLE_LIST_ITEM = "CREATE TABLE "
            + TABLE_ITEM_LIST + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_ITEM_ID + " INTEGER," + KEY_LIST_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_GROCERYITEM);
        db.execSQL(CREATE_TABLE_GROCERY_LIST);
        db.execSQL(CREATE_TABLE_LIST_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_LIST);

        // create new tables
        onCreate(db);
    }



    // ------------------------ "groceryItem" table methods ----------------//

    /*
     * Creating a groceryItem
     */
    public long createGroceryItem(GroceryItem groceryItem, long[] list_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_NAME, groceryItem.getName());
        values.put(KEY_QUANTITY, groceryItem.getQuantity()); // farwa -
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long item_id = db.insert(TABLE_ITEM, null, values);


        // insert list_ids
        for (long list_id : list_ids) {
            createItemList(item_id, list_id);
        }

        return item_id;
    }

    /*
     * get single groceryItem
     */
    public GroceryItem getGroceryItem(long item_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEM + " WHERE "
                + KEY_ID + " = " + item_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        GroceryItem td = new GroceryItem();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName((c.getString(c.getColumnIndex(KEY_ITEM_NAME))));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
    }

    /**
     * getting all groceryItem
     * */
    public List<GroceryItem> getAllGroceryItems() {
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>();
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                GroceryItem td = new GroceryItem();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_ITEM_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to grocery list
                groceryItems.add(td);
            } while (c.moveToNext());
        }

        return groceryItems;
    }


    /**
     * getting all groceryItem under single groceryList NAME
     * */
    public List<GroceryItem> getAllGroceryItemsByGroceryListByName(String groceryList_name) {
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>();

        String selectQuery = "SELECT  * FROM " + TABLE_ITEM + " td, "
                + TABLE_LIST + " tg, " + TABLE_ITEM_LIST + " tt WHERE tg."
                + KEY_LIST_NAME + " = '" + groceryList_name + "'" + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_LIST_ID + " AND td." + KEY_ID + " = "
                + "tt." + KEY_ITEM_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                GroceryItem td = new GroceryItem();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_ITEM_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
                td.setQuantity(c.getInt((c.getColumnIndex(KEY_QUANTITY))));

                // adding to grocery list
                groceryItems.add(td);
            } while (c.moveToNext());
        }

        return groceryItems;
    }

    /**
     * getting all groceryItem under single groceryList ID
     * */
    public List<GroceryItem> getAllGroceryItemsByGroceryListByID(long list_id) {
        List<GroceryItem> groceryItems = new ArrayList<GroceryItem>();

        String selectQuery = "SELECT  * FROM "
                + TABLE_ITEM + " ti, "
                + TABLE_LIST + " tl, "
                + TABLE_ITEM_LIST + " til " +
                "WHERE kli."
                + KEY_LIST_ID + " = '" + list_id + "'" + " AND ki."
                + KEY_ID + " = " + "tt." + KEY_LIST_ID +
                " AND td." + KEY_ID + " = "	+ "tt." + KEY_ITEM_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                GroceryItem td = new GroceryItem();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setName((c.getString(c.getColumnIndex(KEY_ITEM_NAME))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to grocery list
                groceryItems.add(td);
            } while (c.moveToNext());
        }

        return groceryItems;
    }

    /*
     * getting groceryItem count
     */
    public int getGroceryItemCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Updating a groceryItem
     */
    public int updateGroceryItem(GroceryItem groceryItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_NAME, groceryItem.getName());
        values.put(KEY_QUANTITY, groceryItem.getQuantity());

        // updating row
        return db.update(TABLE_ITEM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(groceryItem.getId()) });
    }

    /*
     * Deleting a groceryItem
     */
    public void deleteGroceryItem(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEM, KEY_ID + " = ?",
                new String[] { String.valueOf(tado_id) });
    }

    // ------------------------ "groceryList" table methods ----------------//

    /*
     * Creating groceryList
     */
    public long createGroceryList(GroceryList groceryList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIST_NAME, groceryList.getName());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long groceryList_id = db.insert(TABLE_LIST, null, values);

        return groceryList_id;
    }

    /**
     * getting all groceryLists
     * */
    public List<GroceryList> getAllGroceryLists() {
        List<GroceryList> groceryLists = new ArrayList<GroceryList>();
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                GroceryList t = new GroceryList();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setName(c.getString(c.getColumnIndex(KEY_LIST_NAME)));

                // adding to groceryLists list
                groceryLists.add(t);
            } while (c.moveToNext());
        }
        return groceryLists;
    }

    /*
     * Updating a groceryList
     */
    public int updateGroceryList(GroceryList groceryList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIST_NAME, groceryList.getName());

        // updating row
        return db.update(TABLE_LIST, values, KEY_ID + " = ?",
                new String[] { String.valueOf(groceryList.getId()) });
    }

    /*
     * Deleting a groceryList
     */
    public void deleteGroceryList(int groceryList, boolean should_delete_all_grocery_items) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting groceryList
        // check if items under this groceryList should also be deleted
        if (should_delete_all_grocery_items) {
            // get all items under this groceryList
            List<GroceryItem> allListItems = getAllGroceryItemsByGroceryListByID(groceryList);

            // delete all items
            for (GroceryItem groceryItem : allListItems) {
                // delete groceryItem

                //BUG HERE NEED TO FIND A GOOD WAY TO GRAB THE ID... getID() wont work...
                deleteGroceryItem(groceryItem.getId());
            }
        }

        // now delete the groceryList
        db.delete(TABLE_LIST, KEY_ID + " = ?",
                new String[] { String.valueOf(groceryList) });
    }

    /*
    * getting groceryList count
    */
    public int getGroceryListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }



    // ------------------------ "item_list" table methods ----------------//

    /*
     * Creating item_list
     */
    public long createItemList(long item_id, long list_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_ID, item_id);
        values.put(KEY_LIST_ID, list_id);
        values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_ITEM_LIST, null, values);

        return id;
    }

    /*
     * Updating a item_list
     */
    public int updateItemList(long id, long list_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LIST_ID, list_id);

        // updating row
        return db.update(TABLE_ITEM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    /*
     * Deleting a item_list
     */
    public void deleteItemList(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEM, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }








    //AndroidDatabaseManager Helper
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }




    /**
     * Removes all data from the DB
     */
    public void resetDataBase(Context context)
    {

        context.deleteDatabase(DATABASE_NAME);

//        db.execSQL("DROP TABLE "+ TABLE_ITEM);
//        db.execSQL("DROP TABLE "+ TABLE_LIST);
//        db.execSQL("DROP TABLE "+ TABLE_ITEM_LIST);
//
//        db.execSQL(CREATE_TABLE_GROCERYITEM);
//        db.execSQL(CREATE_TABLE_GROCERY_LIST);
//        db.execSQL(CREATE_TABLE_LIST_ITEM);

    }









}
