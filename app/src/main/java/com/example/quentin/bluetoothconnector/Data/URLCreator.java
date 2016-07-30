package com.example.quentin.bluetoothconnector.Data;

/**
 * Created by Quentin on 6/20/16.
 *
 * Description: abstract interface defined for refactorings;
 * any class that implements this interface should override
 * the <em>createURL</em>: combine data the class possess
 * to create a URL link that is used to send message to
 * the server with get method;
 */
public interface URLCreator {
    String createURL();
}
