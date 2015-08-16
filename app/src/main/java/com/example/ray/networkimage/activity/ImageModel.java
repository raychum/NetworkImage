package com.example.ray.networkimage.activity;

import java.util.ArrayList;

/**
 * Created by Ray on 14/8/15.
 */
public class ImageModel {
    public ResponseData responseData;
    public int responseStatus;

    public class ResponseData {
        public ArrayList<Result> results;

        @Override
        public String toString() {
            return "ResponseData{" +
                    "results=" + results +
                    '}';
        }
    }

    public class Result {
        public String url;
        public int width;
        public int height;

        @Override
        public String toString() {
            return "Result{" +
                    "url='" + url + '\'' +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "responseData=" + responseData +
                ", responseStatus=" + responseStatus +
                '}';
    }
}
