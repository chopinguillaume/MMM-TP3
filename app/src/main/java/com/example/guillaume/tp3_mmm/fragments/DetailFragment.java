package com.example.guillaume.tp3_mmm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.guillaume.tp3_mmm.R;
import com.example.guillaume.tp3_mmm.model.Region;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_REGION = "region";

    private Region region;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param region Region
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(Region region) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_REGION, region);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            region = getArguments().getParcelable(ARG_REGION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        loadURL(view);

        return view;
    }

    private void loadURL(View view) {

        WebView webview = (WebView) view.findViewById(R.id.webview_details);

        String url;
        if (region != null) {
            url = region.getUrl();
        } else {
            url = getResources().getString(R.string.default_url);
        }

        webview.loadUrl(url);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
