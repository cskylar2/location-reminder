package com.example.locationreminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class DateDisplayPicker extends TextView implements DatePickerDialog.OnDateSetListener{

	private Context _context;

	public DateDisplayPicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		_context = context;
		setDate();
	}

	public DateDisplayPicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		_context = context;
		setDate();
		setAttributes();
	}

	public DateDisplayPicker(Context context) {
		super(context);
		_context = context;
		setDate();
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
		final Calendar c = Calendar.getInstance();
		DatePickerDialog dp = new DatePickerDialog(_context, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		dp.show();
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		setText(String.format("%s/%s/%s", monthOfYear+1, dayOfMonth, year));
	}
	
	private void setDate()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		setText(String.format("%s/%s/%s", month+1, day, year));
	}
}