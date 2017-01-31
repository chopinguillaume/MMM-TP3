package com.example.guillaume.tp3_mmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.guillaume.tp3_mmm.fragments.DetailFragment;
import com.example.guillaume.tp3_mmm.fragments.RegionListFragment;
import com.example.guillaume.tp3_mmm.model.Region;

public class MainActivity extends AppCompatActivity implements RegionListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.frame_region_list) != null) {
            if (savedInstanceState != null) {
                return;
            }

            RegionListFragment regionListFragment = new RegionListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_region_list, regionListFragment).commit();
        }

    }

    @Override
    public void onListFragmentInteraction(Region item) {
        if (findViewById(R.id.frame_region_detail) != null) {
            DetailFragment detailFragment = DetailFragment.newInstance(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_region_detail, detailFragment).addToBackStack(null).commit();
        } else {
            Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
            i.putExtra("region", item);
            startActivity(i);
        }
    }
}
