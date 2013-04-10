package com.example.locationreminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeDisplayPicker extends TextView implements TimePickerDialog.OnTimeSetListener {

	private Context _context;

	public TimeDisplayPicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		_context = context;
		setTime();
	}

	public TimeDisplayPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		_context = context;
		setTime();
		setAttributes();
	}

	public TimeDisplayPicker(Context context) {
		super(context);
		_context = context;
		setTime();
		setAttributes();
	}

	private void setAttributes() {
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDateDialog();
			}
		});
	}

	private void showDateDialog() {
		Calendar cal = new GregorianCalendar();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		TimePickerDialog dp = new TimePickerDialog(_context, this, hour, min, false);
		dp.show();
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		setText(String.format("%s:%s", hourOfDay, minute));
	}
	
	private void setTime()
	{
		Calendar cal = new GregorianCalendar();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		setText(String.format("%s:%s", hour, min));
	}
}