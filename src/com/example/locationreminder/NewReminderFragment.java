package com.example.locationreminder;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.example.locationreminder.NewReminderHelper.EventInfo;

public class NewReminderFragment extends Fragment {

	private OnCreateEventListener mOnCreateEventListener;
	
	public interface OnCreateEventListener
	{
		 void createEvent();
	}
	
	@Override
	public void onAttach(Activity activity) {
        super.onAttach(activity);
        mOnCreateEventListener = (OnCreateEventListener)activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//mContext.getActionBar().setDisplayOptions(ActionBar.NAVIGATION_MODE_STANDARD|ActionBar.DISPLAY_USE_LOGO);
		//View view = inflater.inflate(R.layout.fragment_new_reminder_data_time, container, false);
		
		// setup the custom action bar (cancel, done)
		View actionBarButtons = inflater.inflate(R.layout.edit_event_custom_actionbar, container, false);

		View doneButton = actionBarButtons.findViewById(R.id.doneBtnTextID);
		doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mOnCreateEventListener.createEvent();
			}
		});
		 getActivity().getActionBar().setCustomView(actionBarButtons, 
				 new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
	    // return view;
		 return null;
	}
}
