package greenhunan.aircheck.views;

/**
 * Created by Quentin on 8/13/16.
 */
public interface ConnectionCallback {

    void onResponse(int result);
    void onFailure();

}
