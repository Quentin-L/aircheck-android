package greenhunan.aircheck.views;

/**
 * Created by Quentin on 8/13/16.
 */
public interface LoginCallback {

    void onSuccess(String success);
    void onFailure(String failureInfo);

}
