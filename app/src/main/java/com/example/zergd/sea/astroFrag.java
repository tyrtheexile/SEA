package com.example.zergd.sea;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link astroFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link astroFrag#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class astroFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.astro_frag, container, false);
        TextView air = (TextView) view.findViewById(R.id.airText);
        air.setText("100");
        return view;
    }
}
