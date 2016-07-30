package com.example.quentin.bluetoothconnector.Data.DataObject;

import android.support.annotation.Nullable;

/**
 * Data class for storing PM2.5 and temperature info
 *
 * Created by Quentin on 6/14/16.
 */
public class PMAndTempData {

    public static final PMAndTempData nullObject
            = new PMAndTempData("null", "null");

    private String _PM;
    private String _temp;

    private PMAndTempData(String temp, String PM) {
        _PM = PM.trim();
        _temp = temp.trim();
    }

    public PMAndTempData() {}

    public void set_temp(String _temp) {
        this._temp = _temp;
    }

    public void set_PM(String _PM) {
        this._PM = _PM;
    }

    public String getTemp() {
        return _temp;
    }

    public String getPM() {
        return _PM;
    }
}
