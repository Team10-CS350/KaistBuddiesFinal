package com.example.buddyapp4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ProfileFragmentListener listener;

    private TextView logout, name, email, userType;


    public interface ProfileFragmentListener {
        void logOutHandler();
    }

    private User user;
    private String mParam2;



    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(User user, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, user);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);



        logout = v.findViewById(R.id.logOutButton);
        name = v.findViewById(R.id.profileUserName);
        email = v.findViewById(R.id.profileEmail);
        userType = v.findViewById(R.id.userType);

        name.setText(user.getName());
        email.setText(user.getEmail());

        String userTypeString = user.isBuddy()? "Buddy": "International";
        userType.setText(userTypeString);


        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.logOutHandler();
            }
        });


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProfileFragmentListener) {
            listener = (ProfileFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement profile fragment listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
