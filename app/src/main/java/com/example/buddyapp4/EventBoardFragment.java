package com.example.buddyapp4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventBoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /* test code */
    ArrayList<String> titles;
    ArrayList<String> descriptions;
    ArrayList<String> eventTypeStrings;
    int images[] = {R.drawable.avatar, R.drawable.avatar};
    ListView eventListView;
    ArrayList<String> eventNames;
    ArrayAdapter<String> adapter;



    private OnFragmentInteractionListener mListener;

    public EventBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventBoardFragment newInstance(String param1, String param2) {
        EventBoardFragment fragment = new EventBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_board, container, false);

        titles = DemoServer.getEventTitles();
        descriptions = DemoServer.getEventDescriptions();
        eventTypeStrings = DemoServer.getEventTypeStringTags();

        eventListView = v.findViewById(R.id.eventListView);
        MyAdapter adapter = new MyAdapter(getActivity(), titles, descriptions, images, eventTypeStrings);
        eventListView.setAdapter(adapter);

        eventListView.setAdapter(adapter);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.openEventPage(position);

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> rTitle;
        ArrayList<String> rDescription;
        int rImages[];
        ArrayList<String> rTags;

        MyAdapter(Activity activity, ArrayList<String> titles, ArrayList<String> desc, int images[], ArrayList<String> eventTags) {
            super(activity.getApplicationContext(), R.layout.event_row, R.id.mainTitle, titles);
            this.context = context;
            this.rTitle = titles;
            this.rDescription = desc;
            this.rImages = images;
            this.rTags = eventTags;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.event_row, parent, false);
            ImageView image = row.findViewById(R.id.listImage);
            TextView title = row.findViewById(R.id.mainTitle);
            TextView description = row.findViewById(R.id.description);
            TextView tag = row.findViewById(R.id.eventTypeTag);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
            RoundedBitmapDrawable roundedBitmap = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmap.setCircular(true);


//            image.setImageResource(rImages[position]);
            image.setImageDrawable(roundedBitmap);
            title.setText(rTitle.get(position));
            description.setText(rDescription.get(position));
            tag.setText(rTags.get(position));
            tag.setTextColor(getResources().getColor(R.color.colorMellowPrimary));
            tag.setTextSize(12);

            return row;
        }

    }


}
