package com.example.guillaume.tp3_mmm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse {

    public List<Result> results;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {

        public String formatted_address;
        public Geometry geometry;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Geometry {
            public Location location;

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Location {
                public double lat;
                public double lng;

                @Override
                public String toString() {
                    return "Location{" +
                            "lat=" + lat +
                            ", lng=" + lng +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Geometry{" +
                        "location=" + location +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Result{" +
                    "formatted_address='" + formatted_address + '\'' +
                    ", geometry=" + geometry +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GeocodeResponse{" +
                "results=" + results +
                '}';
    }
}
