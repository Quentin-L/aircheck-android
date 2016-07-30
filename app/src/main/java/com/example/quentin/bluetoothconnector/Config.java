package com.example.quentin.bluetoothconnector;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Quentin on 6/30/16.
 *
 * This class is used to store configuration
 * of the phone on which the app runs
 */
public class Config {
    private static String filePath;

    public static void setFilePath(String filePath) {
        Config.filePath = filePath;
    }

    // TODO: 6/30/16
    public static int widthOfPhone;
    public static int heightOfPhone;

    public static final long waitingTime_MilSec = 30000;
    public static final int waitingTime_Sec = 30;

    //server address
    private static final String serverAddress = "http://45.32.39.96:8080";

    public static String getServerAddress() {
        return serverAddress;
    }

    public static String getFilePath() {
        return filePath;
    }
}
