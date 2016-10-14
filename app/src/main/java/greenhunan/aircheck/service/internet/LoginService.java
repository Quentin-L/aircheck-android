package greenhunan.aircheck.service.internet;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.net.HttpCookie;

import greenhunan.aircheck.BuildConfig;
import greenhunan.aircheck.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Quentin on 9/20/16.
 */
public class LoginService {

    static final String TAG = "LoginService";

    public static int login(final User user) {
        final State state = new State();

        Call<String> token = ConnectionService.requestService.getToken();
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, "retrieve token success");
                String token = response.body();

                Call<HttpCookie> login = ConnectionService.requestService.login(token, user);
                login.enqueue(new Callback<HttpCookie>() {
                    @Override
                    public void onResponse(Call<HttpCookie> call, Response<HttpCookie> response) {
                        System.out.println(response.body().getValue());
                        if (response.body() != null) {
                            state.setState(ConnectionService.COONECTION_SUCCESS_PASS);
                            Log.i(TAG, "login success");
                        }
                        else {
                            state.setState(ConnectionService.COONECTION_SUCCESS_FAIL);
                            Log.i(TAG, "login failure");
                        }
                    }
                    @Override
                    public void onFailure(Call<HttpCookie> call, Throwable t) {
                        state.setState(ConnectionService.CONNECTION_FAILURE);
                        Log.e(TAG, "login failure", t);
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                state.setState(ConnectionService.CONNECTION_FAILURE);
                Log.e(TAG, "getting token failure", t);
            }
        });

        return state.getState();

    }

    private static class State {
        int state = -999;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            if (BuildConfig.DEBUG && (state == -999)) // equals to assert state != -999
                throw new AssertionError();
            this.state = state;
        }
    }

}
