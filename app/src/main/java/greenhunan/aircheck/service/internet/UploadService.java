package greenhunan.aircheck.service.internet;

import android.util.Log;

import greenhunan.aircheck.GlobalConfig;
import greenhunan.aircheck.model.SampleData;
import greenhunan.aircheck.service.internet.RequestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Quentin on 9/18/16.
 */
public class UploadService {

    static final String TAG = "UploadService";

    public static void update(final SampleData data) {
        Call<String> tokened_call = ConnectionService.requestService.getToken();
        tokened_call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();

                Call<String> update = ConnectionService.requestService.update(token, data);
                update.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i(TAG, response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e(TAG, "upload data failure", t);
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "getting token failure", t);
            }
        });


    }

}
