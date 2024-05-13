package com.example.tastetrek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, "FoodRecipes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("CREATE TABLE Recipe (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, INGREDIENTS TEXT, DESCRIPTION TEXT, MEMBER_ID INTEGER, FOREIGN KEY (MEMBER_ID) REFERENCES Member(ID));");
        DB.execSQL("CREATE TABLE Member (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT UNIQUE, EMAIL TEXT UNIQUE, CONTACT TEXT, PASSWORD TEXT, NUMBER_OF_POSTS INTEGER DEFAULT 0 CHECK(NUMBER_OF_POSTS >= 0));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Recipe");
        DB.execSQL("drop Table if exists Member");
    }

    public Boolean insertRecipe(String title, String ingredients, String description,Integer memberId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", title);
        contentValues.put("INGREDIENTS", ingredients);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("MEMBER_ID", memberId);
        long result=DB.insert("Recipe", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getRecipe (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Recipe WHERE MEMBER_ID=?", new String[]{id});
        return cursor;
    }

    public Cursor getAllRecipe ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Recipe ", null);
        return cursor;
    }

    public Boolean deleteRecipe (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Recipe where ID = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Recipe", "ID=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean insertmemberdata(String memUName, String memEmail, String memContact, String pwd)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", memUName);
        contentValues.put("EMAIL", memEmail);
        contentValues.put("CONTACT", memContact);
        contentValues.put("PASSWORD", pwd);
        long result=DB.insert("Member", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updatememberdata(String memUName, String memEmail, String memContact, String pwd)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (memUName != null) {
            contentValues.put("USERNAME", memUName);
        }
        if (memEmail != null) {
            contentValues.put("EMAIL", memEmail);
        }
        if (memContact != null) {
            contentValues.put("CONTACT", memContact);
        }
        if (pwd != null) {
            contentValues.put("PASSWORD", pwd);
        }
        Cursor cursor = DB.rawQuery("Select * from Member where USERNAME = ?", new String[]{memUName});
        if (cursor.getCount() > 0) {
            long result = DB.update("Member", contentValues, "USERNAME=?", new String[]{memUName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletememberdata (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Member where ID = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Member", "ID=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getmemberdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Member", null);
        return cursor;
    }

    public boolean checkMemberCredentials(String memberUserName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Member WHERE USERNAME=? AND PASSWORD=?", new String[]{memberUserName, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }


}