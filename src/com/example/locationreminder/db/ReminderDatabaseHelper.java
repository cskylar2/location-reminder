package com.example.locationreminder.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ReminderDatabaseHelper extends SQLiteOpenHelper {

		// column title
		private static final String KEY_ID = "ID";
		private static final String KEY_DEFINITION = "Title";
		private static final String KEY_DATE = "Date";
		
		private static final String DATABASE_NAME = "reminderDB";
		private static final int DATABASE_VERSION = 2;
	    private static final String REMINDER_TABLE_NAME = "reminder";
	    private static final String REMINDER_TABLE_CREATE =
	                "CREATE TABLE " + DATABASE_NAME +"." + REMINDER_TABLE_NAME + " (" +
	                KEY_ID + " INTEGER PRIMARY KEY, " +
	                KEY_DEFINITION + " TEXT," +
	                KEY_DATE +"DATE);";

	    public ReminderDatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(REMINDER_TABLE_CREATE);
	    }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
}
