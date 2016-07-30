package com.example.quentin.bluetoothconnector.Data.DataObject;

import android.location.Location;

/**
 * Data Object for Location info
 *
 * Created by Quentin on 6/14/16.
 */
public class LocationData {

    public static final LocationData nullObject
            = new LocationData("null", "null");

    private String _latitude;
    private String _longitude;

    public LocationData() {}

    private LocationData(String latitude, String longitude) {
        _latitude = latitude;
        _longitude = longitude;
    }

    public void setLatitude(String _latitude) {
        this._latitude = _latitude;
    }

    public void setLongitude(String _longitude) {
        this._longitude = _longitude;
    }

    public String getLatitude() {
        return _latitude;
    }

    public String getLongitude() {
        return _longitude;
    }

}
