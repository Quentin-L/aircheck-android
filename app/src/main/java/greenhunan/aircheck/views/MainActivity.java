package greenhunan.aircheck.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;
import greenhunan.aircheck.R;
import greenhunan.aircheck.service.DataService;

public class MainActivity extends AppCompatActivity {

    public static final String Message = "data";

    private static final String TAG = "Main Activity";

    int countdown = 0;

    BluetoothSPP bt;

    boolean isBluetoothAvailavle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bt = new BluetoothSPP(getApplicationContext());
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            @Override
            public void onDataReceived(byte[] data, String message) {
                messageCountdown(message);
            }
        });
        if (!bt.isBluetoothAvailable())
            setup();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (bt.isBluetoothAvailable())
            setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

//        noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_login:
                Log.i(TAG, "option item Action Login pressed");
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            case R.id.action_bluetooth_connect:
                Log.i(TAG, "bluetooth connect");
                Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if(resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if(resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
//                setup();
            } else {
                // Do something if user doesn't choose any device (Pressed back)
            }
        }
    }

    void messageCountdown(String message) {
        if (++countdown == 30) {
            countdown = 0;
            Intent intent = new Intent(getApplicationContext(), DataService.class);
            intent.putExtra(Message, message);
            startService(intent);
        }
    }

    void setup() {
        Toast.makeText(getApplicationContext(),
                R.string.bluetooth_open, Toast.LENGTH_SHORT).show();
        isBluetoothAvailavle = false;
    }
}
