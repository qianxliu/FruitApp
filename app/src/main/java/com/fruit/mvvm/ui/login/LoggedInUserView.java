package com.fruit.mvvm.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
public class LoggedInUserView {
    private String displayName;
    private String userId;
    //... other data fields that may be accessible to the UI

    public LoggedInUserView(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    String getUserId()
    {
        return userId;
    }

    String getDisplayName() {
        return displayName;
    }
}