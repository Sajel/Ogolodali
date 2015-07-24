package com.ogolodali.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ogolodali.app.model.Ingredient;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sajel on 07.07.2015.
 */
public class FridgeService {

    private DBHelper dbHelper;

    private SQLiteDatabase db;

    public FridgeService(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void setFridge(Set<Ingredient> ingredients) {
        db.delete(DBHelper.fridgeTableName, null, null);
        ContentValues cv = new ContentValues();
        for (Ingredient ingredient : ingredients) {
            cv.put(DBHelper.ingredientIdColName, ingredient.getId());
            cv.put(DBHelper.ingredientNameColName, ingredient.getName());
            db.insert(DBHelper.fridgeTableName, null, cv);
        }
    }

    public Set<Ingredient> getFridge() {
        Set<Ingredient> fridge = null;
        Cursor cursor = db.rawQuery("select * from " + DBHelper.fridgeTableName, null);
        if (cursor.moveToFirst()) {
            fridge = new HashSet<Ingredient>();
            int ingredientIdColIndex = cursor.getColumnIndex(DBHelper.ingredientIdColName);
            int ingredientNameColIndex = cursor.getColumnIndex(DBHelper.ingredientNameColName);
            do {
                String ingedientId = cursor.getString(ingredientIdColIndex);
                String ingredientName = cursor.getString(ingredientNameColIndex);
                Ingredient ingredient = new Ingredient();
                ingredient.setId(ingedientId);
                ingredient.setName(ingredientName);
                fridge.add(ingredient);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return fridge;
    }

}
