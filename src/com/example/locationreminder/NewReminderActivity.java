package com.example.locationreminder;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.locationreminder.NewReminderFragment.OnCreateEventListener;
import com.example.locationreminder.NewReminderHelper.EventInfo;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class NewReminderActivity extends Activity implements  ActionBar.TabListener, OnCreateEventListener {

	private View mCustomView;
	private OnEventAddedListener mOnEventAddedListener;
	
	public NewReminderActivity(OnEventAddedListener listener) {
		mOnEventAddedListener = listener;
	}
	
	/*
	 * Notify the main activity to add a new event
	 */
	public interface OnEventAddedListener
	{
		 void createEvent(EventInfo info);
//		 void setListener(OnEventAddedListener listener);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set up the layout
		setContentView(R.layout.activity_new_reminder);
		mOnEventAddedListener = (OnEventAddedListener)getIntent().getExtras().get("com.example.locationreminder.test");
		
		// now set up the fragment to the Frame layout
		if (savedInstanceState == null) {
            Fragment newFragment = new NewReminderFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment, newFragment).commit();
        }
		
		Spinner repSpinner = (Spinner)findViewById(R.id.repetition_spinnerID);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.repetition, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		repSpinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_reminder, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onCheckBoxClick(View view)
	{
		ViewFlipper flipper = (ViewFlipper)findViewById(R.id.viewFlipperID);
		if(flipper != null)
			flipper.showNext();
	}

	@Override
	public void createEvent() {
		// TODO Auto-generated method stub
		EditText nameField = (EditText)findViewById(R.id.newReminderID);
		String titleText = new String("");
		if(nameField != null) {
			titleText =	nameField.getText().toString();
		}
		 
		View dateTimeView = (View)findViewById(R.id.dataTimeLayoutID);
		TextView date = null;
		TextView time = null;
		EditText address = null;
		
		if(dateTimeView != null) {
			date = (TextView)findViewById(R.id.dateDisplayPickerID);
			time = (TextView)findViewById(R.id.timeDisplayPickerID);
			
		} else {
			// check if address layout is shown
			address = (EditText)findViewById(R.id.addressID);
		}
		EventInfo event = new EventInfo();
		event.mTitle = titleText;
		if(date != null && time != null) {
			int year = 0, month = 0, day = 0, hour = 0, min = 0;
			String dateString = date.getText().toString();
			String timeString = time.getText().toString();
			String dateTime = dateString + " " + timeString;
			
			// FIXME: does that switch when in Europe locale? I think it does
			SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy k:m");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormatter.parse(dateTime, new ParsePosition(0)));
			month  = calendar.get(Calendar.MONTH) + 1;
			day  = calendar.get(Calendar.DATE);
			year = calendar.get(Calendar.YEAR);
			hour = calendar.get(Calendar.HOUR);
			min = calendar.get(Calendar.MINUTE);
			
			calendar.set(year, month, day);
			Date date1 = calendar.getTime();
			event.mDate = date1;
		}
		mOnEventAddedListener.createEvent(event);
	}
	
	public void setListener(OnEventAddedListener listener) {
		// TODO Auto-generated method stub
		
	}
}
