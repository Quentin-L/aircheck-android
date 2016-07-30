package com.example.quentin.bluetoothconnector.Data.SendService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.quentin.bluetoothconnector.Data.Service;

/**
 * Service for processing data received from detector
 *
 * Created by HereWegoR on 5/17/16.
 */
public class SendService implements Service {
    private static ConnectivityManager _manager;
    private static Context _context;

    private Thread thread;

    public SendService(@NonNull Context context) {
        _context = context;
        _manager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    // TODO: 6/30/16 change it later when the server is online
    @Override
    public void start() {
//        if (isNetworkAvailable()) thread = new SendThread();
//        else {
//            thread = new WriteThread();
//            notifyUserToOpenNetwork();
//        }
        thread = new WriteThread();

        thread.start();
    }

    @Override
    public void stop() {
        try {
            thread.interrupt();
        } catch (Exception e) {
        }
    }

    private boolean isNetworkAvailable() {
        NetworkInfo networkInfo = _manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    private void notifyUserToOpenNetwork() {
        Toast.makeText(_context, "请打开网络",
                Toast.LENGTH_SHORT).show();
    }
}
