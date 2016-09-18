package greenhunan.aircheck.model;

/**
 * Created by Quentin on 9/18/16.
 */
public class SampleData {

    public SampleData(int id, String time, Double density, Double latitude, Double longitude) {
        this.user_id = id;
        this.time = time;
        this.density = density;
        this.lat = latitude;
        this.lng = longitude;
    }

    public int user_id;
    public String time;
    public Double density;
    public Double lat; // latitude
    public Double lng; // longitude
}
