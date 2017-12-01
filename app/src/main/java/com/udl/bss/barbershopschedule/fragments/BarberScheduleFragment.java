package com.udl.bss.barbershopschedule.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.udl.bss.barbershopschedule.R;

import static android.content.ContentValues.TAG;


public class BarberScheduleFragment extends Fragment {
    private static final String TAG= "BarberScheduleFragment";
    private OnFragmentInteractionListener mListener;

    //Section for listing the schedule for selected date
    protected  View mView;
    CalendarView mCalendarView;


    public BarberScheduleFragment() {
        // Required empty public constructor
    }

    public static BarberScheduleFragment newInstance() {
        return new BarberScheduleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_barber_schedule, container, false);

        View view = inflater.inflate(R.layout.fragment_barber_schedule, container, false);
        this.mView = view;


        //Section for listing the schedule for selected date
        mCalendarView = (CalendarView) mView.findViewById(R.id.barbers_schedule_day);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year+ "-" + (month+1) + "-" +dayOfMonth;
                Log.d( TAG, "onSelectedDayChange : started." + date );

                BarberScheduleDateListFragment barberScheduleDateListFragment = new BarberScheduleDateListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("date", date);
                barberScheduleDateListFragment.setArguments(bundle);

                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_home, barberScheduleDateListFragment).
                        commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
