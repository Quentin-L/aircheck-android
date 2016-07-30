package com.example.quentin.bluetoothconnector.Data.SendService;

import android.util.Log;

import com.example.quentin.bluetoothconnector.Config;
import com.example.quentin.bluetoothconnector.Data.PM2_5State;
import com.example.quentin.bluetoothconnector.Data.URLCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * thread used for writing message into local file
 * Created by HereWegoR on 5/22/16.
 */
class WriteThread extends Thread {

    public static final String TAG = "WriteThread";

    private Writer writer;

    private volatile boolean run = true;

    private URLCreator URLCreator = PM2_5State.INSTANCE;

    public WriteThread() {
        try {
            checkFileExists();
            writer = new BufferedWriter(new FileWriter(getFile(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFileExists() throws IOException {
        File file = getFile();
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public void run() {
        super.run();

        while (run) {
            try {
                Thread.sleep(Config.waitingTime_MilSec);
                Log.i(TAG, "--- write message to local file ---");
                writer.write(URLCreator.createURL());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        run = false;
    }

    private File getFile() {
        return new File(Config.getFilePath(), "AirCheck.txt");
    }
}
