package com.example.quentin.bluetoothconnector.Data;

import android.util.Log;

import com.example.quentin.bluetoothconnector.Config;

/**
 * Created by Quentin on 6/20/16.
 *
 * A class for receiving and holding new data;
 * any subclass can retrieve data using
 * <em>get</em> method of this class.
 *
 * <p>As this class implements {@link URLCreator} interface,
 * class that needs to retrieve data and send data to server
 * can directly call <em>createURL</em> to get a URL link and
 * use Http <em>get</em> method to send data</p>
 */
public class PM2_5State implements URLCreator {

    public static final String TAG = "PM 2.5 State";

    public static final PM2_5State INSTANCE = new PM2_5State();

    private static String PM2_5 = " ";
    private static String temp = " ";
    private static String longitude = " ";

    private static String latitude = " ";

    private PM2_5State() {}

    /**
     * create URL to send message to the server
     * by Http get method
     *
     * form the data received from detector into format
     * the format being: [URI]/data/store/?p=[PM2.5]%time=[UNIXTIME]&
     * temp=[TEMPERATURE]%latitude=[LAT]%longitude=[LONG]
     * @return
     */
    public String createURL(){

        Log.i(TAG, "--- a new url created ---");

        StringBuilder result = new StringBuilder();

        result.append(Config.getServerAddress());
        result.append("/data/store/?p=");
        result.append(PM2_5);
        result.append("&time=");
        result.append(Long.valueOf(System.currentTimeMillis()/1000));
        result.append("&temp=");
        result.append(temp);
        result.append("&latitude=");
        result.append(latitude);
        result.append("&longitude=");
        result.append(longitude);

        return result.toString();
    }

    public static void setPm25(String pm25) {
        PM2_5 = pm25;
    }

    public static void setTemp(String temp) {
        PM2_5State.temp = temp;
    }

    public static void setLongitude(String longitude) {
        PM2_5State.longitude = longitude;
    }

    public static void setLatitude(String latitude) {
        PM2_5State.latitude = latitude;
    }

    public static String getPm25() {
        return PM2_5;
    }

    public static String getTemp() {
        return temp;
    }

    public static String getLongitude() {
        return longitude;
    }

    public static String getLatitude() {
        return latitude;
    }
}
