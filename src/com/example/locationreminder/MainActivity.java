package com.example.locationreminder;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Date;

import com.example.locationreminder.NewReminderActivity.OnEventAddedListener;
import com.example.locationreminder.NewReminderHelper.EventInfo;
import com.example.locationreminder.db.DBAdapter;
import com.example.locationreminder.db.ReminderDatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnEventAddedListener, Serializable {

	private ReminderDatabaseHelper mDataBase = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo_Light);
		
		setContentView(R.layout.activity_main);
		
		mDataBase = new ReminderDatabaseHelper(this);
		
		DBAdapter db = new DBAdapter(this);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
		        android.R.layout.simple_list_item_1, 
		        db.getAllTitles(), 
		        new String[] { "title" }, 
		        new int[] { android.R.id.text1 });

		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
		/*
		 * that's the layout for tablets
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_layout);
		for(int i=0; i<4; i++)
		{
			LinearLayout horizontal = new LinearLayout(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
			params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
			params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
			params.weight = 1.0f;
			
			CheckBox checkBox1 = new CheckBox(this);
			EditText text = new EditText(this);
			text.setLayoutParams(params);
			text.setHint(R.string.remainerNameHint);
			text.setCursorVisible(false);
			
			TextView atString = new TextView(this);
			atString.setText(R.string.at_string);
			Spinner spinnerLocation = new Spinner(this);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.location_array, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerLocation.setAdapter(adapter);
			
			horizontal.addView(checkBox1);
			horizontal.addView(text);
			horizontal.addView(atString);
			horizontal.addView(spinnerLocation);
			
			linearLayout.addView(horizontal);
		}
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.action_addReminder)
		{
			Intent intent = new Intent(this, NewReminderActivity.class);
			intent.putExtra("com.example.locationreminder.test", this);
			startActivity(intent);
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void createEvent(EventInfo info) {
		
		String title = info.mTitle;
		Date date = info.mDate;
		String address = info.mAddress;
		
		String insertReminder = "INSERT INTO " + mDataBase.getDatabaseName() + 
				"(Title, Date) values (" + title + "," + date + ");";
		
		mDataBase.getWritableDatabase().execSQL(insertReminder);
		
	}
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in)
		     throws IOException, ClassNotFoundException {

	}

	private void readObjectNoData() 
		     throws ObjectStreamException {
		
	}
}
