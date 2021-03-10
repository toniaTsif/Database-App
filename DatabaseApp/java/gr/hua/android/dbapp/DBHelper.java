package gr.hua.android.dbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB_NAME="MyDB";
    public static String TABLE_NAME="MY_TABLE";
    public static String FIELD_1="userid";
    public static String FIELD_2="longitude";
    public static String FIELD_3="latitude";
    public static String FIELD_4="timestamp";
    private String SQL_QUERY = "CREATE TABLE "+TABLE_NAME+" ("+FIELD_1+" TEXT, "+FIELD_2+" FLOAT, "+FIELD_3+" FLOAT, "+FIELD_4+" TEXT)";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insertContact(ContractRecord contract){
        ContentValues values = new ContentValues();
        values.put(FIELD_1, contract.getUserId());
        values.put(FIELD_2,contract.getLongitude());
        values.put(FIELD_3,contract.getLatitude());
        values.put(FIELD_4,contract.getTimestamp());
        long id =this.getWritableDatabase().insert(TABLE_NAME,null,values);
        return id;
    }

    public Cursor searchContact(ContractRecord contract){
        Cursor cursor = this.getReadableDatabase().query(TABLE_NAME, null,FIELD_1+"=? AND "+FIELD_4+"=?", new String[]{contract.getUserId(),contract.getTimestamp()},null,null,null);
        return cursor;
    }

    public Cursor searchTimestamp(ContractRecord contract){
        Cursor cursor = this.getReadableDatabase().query(TABLE_NAME, new String[]{FIELD_4},null, null,null,null,null);
        return cursor;
    }
}
