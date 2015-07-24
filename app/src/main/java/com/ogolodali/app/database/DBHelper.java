package com.ogolodali.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sajel on 06.07.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";


    static final String dbName = "OgolodaliDB";


    static final String fridgeTableName = "fridge";

    static final String ingredientIdColName = "ingredientId";

    static final String ingredientNameColName = "name";




    public DBHelper(Context context) {
        super(context,dbName,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"onCreate");
        sqLiteDatabase.execSQL("CREATE TABLE" + fridgeTableName + "(\n" +
                "    _id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "\t" + ingredientIdColName + "text NOT NULL,\n" +
                "    " + ingredientNameColName + " text NOT NULL\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
