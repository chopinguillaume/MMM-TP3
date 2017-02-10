package com.example.guillaume.tp3_mmm.util;

import android.os.AsyncTask;
import android.util.Log;

import com.example.guillaume.tp3_mmm.model.Command;
import com.example.guillaume.tp3_mmm.model.GeocodeResponse;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GeocodeJSONRequest extends AsyncTask<String, String, GeocodeResponse> {
    private String geoCoderUrl;
    private Command cmdUpdateLocation;
    private double lat;
    private double lng;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public GeocodeJSONRequest(String url) {
        geoCoderUrl = url;
    }

    @Override
    protected GeocodeResponse doInBackground(String... args) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            return restTemplate.getForObject(geoCoderUrl, GeocodeResponse.class);
        } catch (Exception e) {
            Log.e("GeocodeJSONRequest", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(GeocodeResponse geo) {
        try {
            Log.e("Debug", "URL :: \n" + geoCoderUrl);
            Log.e("Debug", "Reponse :: \n" + geo);

            List<GeocodeResponse.Result> results = geo.results;

            if (results != null && !results.isEmpty()) {
                lat = results.get(0).geometry.location.lat;
                lng = results.get(0).geometry.location.lng;

                if (cmdUpdateLocation != null) {
                    cmdUpdateLocation.execute();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCmdUpdateLocation(Command cmd) {
        cmdUpdateLocation = cmd;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}
