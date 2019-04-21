package com.macdevelopers.moofwd.Utilities;

/**
 * Created by Suraj Nirmal on 31/01/18.
 */

public interface Constants {

    int GET = 0;
    int POST = 1;
    int PUT = 2;
    int DELETE = 3;

    // Milliseconds per second
    public static final int MILLISECONDS_PER_SECOND = 1000;
    // Update frequency in seconds
    public static final int UPDATE_INTERVAL_IN_SECONDS = 10;
    // Update frequency in milliseconds
    public static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    // The fastest update frequency, in seconds
    public static final int FASTEST_INTERVAL_IN_SECONDS = 60;
    // A fast frequency ceiling in milliseconds
    public static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;

    //TODO getProfile
    String getProfile = "http://54.94.179.75:3000/moofwd/users/james@moofwd.com";

    //TODO getContacts
    String getContacts = "http://54.94.179.75:3000/moofwd/contacts";

    //TODO getMobileNo
    String getMobileNo = "+919665997663";

}

