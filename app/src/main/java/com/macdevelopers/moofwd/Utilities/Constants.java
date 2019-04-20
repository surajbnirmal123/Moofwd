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

    //TODO getLiveApi
    String getLiveApi = "https://www.vasaibirds.com/motorbuddy/service_live.php";

    //TODO Facebook Link
    String getFacebookLink = "https://www.facebook.com/MotorBuddyIn";

    //TODO Youtube Link
    String getYoutubeLink = "https://www.youtube.com/channel/UCQG9doH5eIZg2s3Eb4yqhtw";

    //TODO Twitter Link
    String getTwitterLink = "https://twitter.com/MotorBuddy";

    //TODO getPrivacyPolicyLink
    String getPrivacyPolicyLink = "https://www.vasaibirds.com/motorbuddy/privacy_policy.html";

    //TODO getWarrantyLink
    String getWarrantyLink = "https://www.vasaibirds.com/motorbuddy/warranty.html";

    //TODO getDealerLoginLink
    String getDealerLoginLink = "https://www.vasaibirds.com/motorbuddy/login.php";

    //TODO getCheckListPDFLink
    String getCheckListPDFLink = "https://www.vasaibirds.com/motorbuddy/upload_files/checklist/checklist.pdf";

    //TODO getMobileNo
    String getMobileNo = "+919665997663";

}

