package com.example.guillaume.tp3_mmm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.guillaume.tp3_mmm.fragments.DetailFragment;
import com.example.guillaume.tp3_mmm.model.Region;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        if (findViewById(R.id.frame_region_detail) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Region region = new Region("Inconnu");

            Bundle b = getIntent().getExtras();
            if (b != null) {
                region = b.getParcelable("region");
            } else {
                region.setUrl(getResources().getString(R.string.default_url));
            }

            DetailFragment detailFragment = DetailFragment.newInstance(region);
            getSupportFragmentManager().beginTransaction().add(R.id.frame_region_detail, detailFragment).commit();

        }
    }
}
