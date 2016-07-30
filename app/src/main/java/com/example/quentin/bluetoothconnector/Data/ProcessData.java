package com.example.quentin.bluetoothconnector.Data;

import android.support.annotation.Nullable;

import com.example.quentin.bluetoothconnector.Data.DataObject.PMAndTempData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a helper class for processing data using regex
 *
 * Created by Quentin on 6/14/16.
 */
public class ProcessData {

    public static final String REGEX = "T:(.*) C.*avgP:(.*)ug.*";

    private final static PMAndTempData nullObject = PMAndTempData.nullObject;

    public static void process(String message) {
        PMAndTempData data = regex(message);
        PM2_5State.setPm25(data.getPM());
        PM2_5State.setTemp(data.getTemp());
    }

    private static PMAndTempData regex(String message) {
        Matcher matcher = Pattern
                .compile(REGEX)
                .matcher(message);

        if (matcher.find()) {
            PMAndTempData result = new PMAndTempData();
            result.set_temp(matcher.group(1));
            result.set_PM(matcher.group(2));
            return result;
        }
        else
            return nullObject;
    }
}
