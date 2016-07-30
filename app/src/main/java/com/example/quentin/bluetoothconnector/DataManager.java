package com.example.quentin.bluetoothconnector;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;

import com.example.quentin.bluetoothconnector.Data.GUIUpdate;
import com.example.quentin.bluetoothconnector.Data.LocationManger;
import com.example.quentin.bluetoothconnector.Data.SendService.SendService;
import com.example.quentin.bluetoothconnector.Data.ProcessData;
import com.example.quentin.bluetoothconnector.Data.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 6/30/16.
 */
public class DataManager {

    public static final String TAG = "Data Manager";

    public static final boolean MANANGER_RUNNING = true;
    public static final boolean MANAGER_STOPPED = false;

    private List<Service> serviceList = new ArrayList<>();

    private boolean managerState;
    private int loop_count = 29;

    public void send(String message) {
        if (isLoopOver()) {
            Log.i(TAG, "--- sends message ---");
            ProcessData.process(message);
        }
    }

    public void start() {
        if (managerState == MANAGER_STOPPED)
            startService();
    }

    public void stop() {
        if (managerState == MANANGER_RUNNING)
            stopService();
    }

    public DataManager(Context context, Handler handler) {
        Log.i(TAG, "--- Data Manager Activates ---");
        Service messageService = new SendService(context);
        serviceList.add(messageService);
        Service GUIUpdate = new GUIUpdate(handler);
        serviceList.add(GUIUpdate);
        Service locationManager = new LocationManger(context);
        serviceList.add(locationManager);
    }

    private boolean isLoopOver() {
        if (++loop_count != Config.waitingTime_Sec)
            return false;
        else {
            loop_count = 0;
            return true;
        }
    }

    private void startService() {
        for (Service s : serviceList) {
            s.start();
        }

        managerState = MANANGER_RUNNING;
    }

    private void stopService() {
        for (Service s : serviceList) {
            s.stop();
        }

        managerState = MANAGER_STOPPED;
    }

}
