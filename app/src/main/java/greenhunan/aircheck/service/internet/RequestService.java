package greenhunan.aircheck.service.internet;

import java.net.HttpCookie;
import java.util.zip.DeflaterOutputStream;

import greenhunan.aircheck.model.SampleData;
import greenhunan.aircheck.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Quentin on 9/18/16.
 */
public interface RequestService {
    @POST("api/insert?")
    Call<String> update(@Query("token") String token, @Body SampleData data);

    @GET("api/insert")
    Call<String> getToken();

    @POST("user/login?")
    Call<HttpCookie> login(@Query("token") String token, @Body User user);

    @GET("user/logout")
    Call<String> logout();
}
