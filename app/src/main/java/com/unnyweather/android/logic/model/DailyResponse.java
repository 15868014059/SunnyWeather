package com.unnyweather.android.logic.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

//定义数据模型类
public class DailyResponse {
    public String status;
    public Result result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    class Result {
        Daily daily;

    }
    public static  class LifeDescription {
        public    String desc;
    }

    public static  class LifeIndex {
        public  List<LifeDescription> coldRisk;
        public List<LifeDescription> carWashing;
        public  List<LifeDescription> ultraviolet;
        public List<LifeDescription> dressing;
    }

    public static  class Skycon {
        public String value;
        public Date date;
    }

    public static  class Temperature {
        public Float max;
        public  Float min;

    }

    public static   class Daily {
        List<Temperature> temperature;
        List<Skycon> skycon;
        @SerializedName("life_index")
        LifeIndex lifeIndex;

        public List<Temperature> getTemperature() {
            return temperature;
        }

        public void setTemperature(List<Temperature> temperature) {
            this.temperature = temperature;
        }

        public List<Skycon> getSkycon() {
            return skycon;
        }

        public void setSkycon(List<Skycon> skycon) {
            this.skycon = skycon;
        }

        public LifeIndex getLifeIndex() {
            return lifeIndex;
        }

        public void setLifeIndex(LifeIndex lifeIndex) {
            this.lifeIndex = lifeIndex;
        }
    }

}