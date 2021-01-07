package com.unnyweather.android.logic.model;

import com.google.gson.annotations.SerializedName;


public class RealtimeResponse {
    public Result result;
    public String status;

    public static  class Result {
        public  Realtime realtime;

    }

    public static  class AirQuality {
        public AQI aqi;
    }

    public static  class AQI {
        public Float chn;
    }

    public static class Realtime {
        public String skycon;
        public Float temperature;
        @SerializedName("air_quality")
        public AirQuality airQuality;
    }
}
