package greenhunan.aircheck.service;

import greenhunan.aircheck.GlobalConfig;
import greenhunan.aircheck.model.SampleData;
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

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GlobalConfig.base_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private static RequestService requestService = retrofit.create(RequestService.class);

    public static void update(final SampleData data) {
        Call<String> tokened_call = requestService.getToken();
        tokened_call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                System.out.println(token);

                Call<String> update = requestService.update(token, data);
                update.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        System.out.println(response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

}
