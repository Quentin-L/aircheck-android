package com.example.quentin.bluetoothconnector.Data.SendService;

import android.util.Log;

import com.example.quentin.bluetoothconnector.Config;
import com.example.quentin.bluetoothconnector.Data.PM2_5State;
import com.example.quentin.bluetoothconnector.Data.URLCreator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * thread for sending message to the server via the Internet
 *
 * Created by HereWegoR on 5/15/16.
 */
class SendThread extends Thread {

    // debug
    public static final String TAG = "SendThread";

    private PM2_5State data = PM2_5State.INSTANCE;
    private URLCreator urlCreator;

    private volatile boolean run = true;

    public SendThread() {
    }

    @Override
    public void run() {
        super.run();

        while (run) {

            try {
                Thread.sleep(Config.waitingTime_MilSec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.i(TAG, "--- send message to server ---");

            URL url = null;
            try {
                url = new URL(urlCreator.createURL());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");// 设置请求的方式
                urlConnection.setReadTimeout(5000);// 设置超时的时间
                urlConnection.setConnectTimeout(5000);// 设置链接超时的时间
                // 获取响应的状态码 404 200 505 302
                if (urlConnection.getResponseCode() == 200) {
                    Log.i(TAG, TAG + "sent successfully *********************");
                }else {
                    Log.i(TAG, TAG + "------------------链接失败-----------------");
                }
            } catch (MalformedURLException | ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        run = false;
    }
}
