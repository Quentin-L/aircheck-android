package com.example.quentin.bluetoothconnector.Data;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.quentin.bluetoothconnector.Data.DataObject.PMAndTempData;

import java.util.concurrent.ExecutorService;

/**
 * Created by Quentin on 6/14/16.
 *
 *
 * This class is used to separate GUI control
 * from from business logic.
 *
 * <p>This class analyses whether PM 2.5
 * and temperature have changed, and if so, updates
 * UI element for the user to view</p>
 */
public class GUIUpdate implements Service {

    public static final String TAG = "GUI update";

    private float pm2_5 = -99f;
    private float temp = -99f; //temperature

    private Handler _handler;

    private Thread thread;

    public GUIUpdate(Handler handler) {
        _handler = handler;
    }

    private void update() {
        if (dataChange()) {
            Log.i(TAG, "--- UI updates ---");

            Message msg = new Message();
            msg.what = 0;
            PMAndTempData result = new PMAndTempData();
            result.set_PM(String.valueOf(pm2_5));
            result.set_temp(String.valueOf(temp));
            msg.obj = result;
            _handler.sendMessage(msg);

            updateLocalData();
        }
    }

    private void updateLocalData() {
        pm2_5 = Float.valueOf(PM2_5State.getPm25());
        temp = Float.valueOf(PM2_5State.getTemp());
    }

    private boolean dataChange() {
        return pmChange() || tempChange();
    }

    private boolean pmChange() {
        return Math.abs(pm2_5 - Float.valueOf(PM2_5State.getPm25())) > 1;
    }

    private boolean tempChange() {
        return Math.abs(temp - Float.valueOf(PM2_5State.getTemp())) > 0.5;
    }

    @Override
    public void start() {
        thread = new UpdateThread();
        thread.start();
    }

    @Override
    public void stop() {
        try {
            thread.interrupt();
        } catch (Exception e) {
        }
    }

    private class UpdateThread extends Thread {
        private volatile boolean run = true;

        @Override
        public void run() {
            while (run) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                update();
            }
        }

        @Override
        public void interrupt() {
            run = false;
        }
    }

}
