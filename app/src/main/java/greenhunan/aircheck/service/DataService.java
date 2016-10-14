package greenhunan.aircheck.service;

import android.app.IntentService;
import android.content.Intent;

import greenhunan.aircheck.views.MainActivity;

/**
 * Created by Quentin on 10/9/16.
 */
public class DataService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public DataService() {
        super("DataService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String message = intent.getStringExtra(MainActivity.Message);

    }


}
