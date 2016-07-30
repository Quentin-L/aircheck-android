package com.example.quentin.bluetoothconnector.Data;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.example.quentin.bluetoothconnector.Config;
import com.example.quentin.bluetoothconnector.Data.DataObject.LocationData;

import java.util.Observable;
import java.util.Observer;

/**
 * location manager for getting location info
 *
 * Created by HereWegoR on 5/13/16.
 */
public class LocationManger implements Service {

    // debug
    public static final String TAG = "LocationManger";

    private Thread thread;
    private Context context;
    private final LocationManager lm;
    private static final LocationData nullObject = LocationData.nullObject;

    public LocationManger(Context context) {
        Log.i(TAG, "----- Location Manger activates -----");
        this.context = context;
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

//    private void notifyUserToOpenGPS() {
//        Toast.makeText(context, "请打开GPS定位",
//                Toast.LENGTH_SHORT).show();
//    }

    private LocationData getLocation() {
        if (!isGPSAvailable())
            return nullObject;

        Location location = getLocationObject();
        return getLocationDataObject(location);
    }

    private LocationData getLocationDataObject(Location location) {
        if (location == null) {
            return nullObject;
        }
        LocationData result = new LocationData();
        result.setLatitude(
                String.valueOf(location.getLatitude()));
        result.setLongitude(
                String.valueOf(location.getLongitude()));
        return result;
    }

    private Location getLocationObject() {
        Location location = null;
        try {
            location = lm.getLastKnownLocation(
                    lm.getBestProvider(getCriteria(), true));

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            Log.i(TAG, e.toString());
        }
        return location;
    }

    // GPS settings
    private static Criteria getCriteria() {
        Criteria criteria = new Criteria();

        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        return criteria;
    }


    private boolean isGPSAvailable() {
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void start() {
        thread = new LocationThread();
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }


    private class LocationThread extends Thread{
        private volatile boolean run = true;

        @Override
        public void run() {
            while (run) {
                try {
                    Thread.sleep(Config.waitingTime_MilSec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i(TAG, "--- location manager updates location info ---");
                LocationData data =getLocation();
                PM2_5State.setLatitude(data.getLatitude());
                PM2_5State.setLongitude(data.getLongitude());

            }
        }

        @Override
        public void interrupt() {
            run = false;
        }
    }
}
