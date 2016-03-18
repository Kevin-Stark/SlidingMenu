package com.kevin.slidingmenutest.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kevin.slidingmenutest.MainActivity;
import com.kevin.slidingmenutest.R;

/**
 * Created by TVKevin on 2016/3/17.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {
    private Button btn1, btn2, btn3;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_menu, container, false);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        activity = (MainActivity) getActivity();
        activity.setFragment(1);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                activity.setFragment(1);
                break;
            case R.id.btn2:
                activity.setFragment(2);
                break;
            case R.id.btn3:
                activity.setFragment(3);
                break;
            default:
                break;
        }
        activity.closeDraw();
    }
}
