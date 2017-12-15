package adrianbeukes.question2;

/**
 * Created by Adrian on 2017/05/19.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_PRODUCT_NAME = "productName";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_QUANTITY = "quantity";
    public static final String TAG = "DBAdapter";

    public final static String DATABASE_NAME = "Surf_Shop";
    public final static String DATABASE_TABLE = "tbl_surf";
    public final static int DATABASE_VERSION = 1;

    public static  final String DATABASE_CREATE = "create table tbl_surf(_id integer primary key autoincrement, productName text not null, description text not null, price text not null, quantity text not null);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    //**************************************************************************
    public DbAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    //**************************************************************************

    //Inner class for DB Helper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        //Constructor
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //On Create method
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //**************************************************************************
        // Upgrade Method
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading DB from version : " + oldVersion + " to : " + newVersion + " Which will destroy all data on it ");
            db.execSQL("DROP TABLE IF EXISTS tbl_surf");
            onCreate(db);
        }
        //**************************************************************************
    }
    //**************************************************************************
    // Open DB Connection
    public DbAdapter open ()throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //**************************************************************************
    //Close Connection
    public void close()
    {
        DBHelper.close();
    }

    //**************************************************************************
    public long insertItems(String name, String description, String price, String quantity)
    {
        try {
            db.execSQL(DATABASE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PRODUCT_NAME, name);
        initialValues.put(KEY_DESCRIPTION,description);
        initialValues.put(KEY_PRICE,price);
        initialValues.put(KEY_QUANTITY,quantity);
        return db.insert(DATABASE_TABLE,null,initialValues);
    }

    //**************************************************************************
    public boolean deleteItem(long rowId)
    {
        return db.delete(DATABASE_TABLE,KEY_ROWID+"="+rowId,null)>0;
    }

    //**************************************************************************
    public Cursor getAllItems()
    {
        return db.query(DATABASE_TABLE,new String[] {KEY_ROWID, KEY_PRODUCT_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_QUANTITY},null,null,null,null,null,null);
    }

    //**************************************************************************
    public Cursor getItem(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(DATABASE_TABLE,new String[] {KEY_ROWID, KEY_PRODUCT_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_QUANTITY}, KEY_ROWID + "=" + rowId,null,null,null,null,null);
        if (mCursor!= null)
        {
            mCursor.moveToFirst();
        }
        return  mCursor;
    }

    //**************************************************************************
    public boolean updateItems(long rowId, String name, String desc, String price, String quantity)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PRODUCT_NAME, name);
        initialValues.put(KEY_DESCRIPTION,desc);
        initialValues.put(KEY_PRICE,price);
        initialValues.put(KEY_QUANTITY,quantity);
        return db.update(DATABASE_TABLE,initialValues,KEY_ROWID+"="+rowId,null)>0;
    }

    //**************************************************************************
}
