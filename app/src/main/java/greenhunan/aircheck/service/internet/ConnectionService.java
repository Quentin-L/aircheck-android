package greenhunan.aircheck.service.internet;

import greenhunan.aircheck.GlobalConfig;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Quentin on 9/20/16.
 */
public class ConnectionService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GlobalConfig.base_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public static RequestService requestService = retrofit.create(RequestService.class);

    public static final int COONECTION_SUCCESS_PASS = 1;
    public static final int COONECTION_SUCCESS_FAIL = 2;
    public static final int CONNECTION_FAILURE = 3;
}
