package com.mapbox.mapboxandroiddemo.analytics;


import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxandroiddemo.R;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.IdentifyMessage;
import com.segment.analytics.messages.ScreenMessage;
import com.segment.analytics.messages.TrackMessage;

import java.util.HashMap;

/**
 * Created by langstonsmith on 4/25/17.
 */

public class AnalyticsTracker {

  private static final AnalyticsTracker INSTANCE = new AnalyticsTracker();

  private String openedApp = "Opened App";
  private String clickedOnSignInButtonEventName = "Clicked On Sign In Button";
  private String clickedOnCreateAccountButtonEventName = "Clicked On Create Account Button";
  private String clickedOnNavDrawerSectionEventName = "Clicked On Nav Drawer Section";
  private String clickedOnIndividualExampleEventName = "Clicked On Individual Example";


  public static AnalyticsTracker get() {

    return INSTANCE;
  }

  private Analytics analytics = Analytics.builder("zFLtBpautarTslr61PUbvEKXXLIoLRmq").build();

  public String mapboxUsername = "LangstonSmithTestUsername";


  public void openedAppForFirstTime(@NonNull String userID) {


    HashMap<String, String> properties = new HashMap<>();

    properties.put("Device model", Build.MODEL);
    properties.put("Device brand", Build.BRAND);
    properties.put("Device product", Build.PRODUCT);
    properties.put("Device manufacturer", Build.MANUFACTURER);

    analytics.enqueue(TrackMessage.builder("Opened App For First Time")
      .userId(mapboxUsername)
      .properties(properties)
    );

  }

  public void openedApp() {
    trackEvent(openedApp, null, null);
  }


  public void clickedOnSignInButton() {
    trackEvent(clickedOnSignInButtonEventName, null, null);
  }

  public void clickedOnCreateAccountButton() {
    trackEvent(clickedOnCreateAccountButtonEventName, null, null);
  }


  public void clickedOnNavDrawerSection(@NonNull String sectionName) {
    trackEvent(clickedOnNavDrawerSectionEventName, "Section name", sectionName);

  }

  public void clickedOnIndividualExample(@NonNull String exampleName) {
    trackEvent(clickedOnIndividualExampleEventName, "Example name", exampleName);
  }

  public void trackEvent(@NonNull String eventName, String keyForPropertiesHashMap, String valueForPropertiesHashMap) {


    if (keyForPropertiesHashMap == null || valueForPropertiesHashMap == null) {
      analytics.enqueue(TrackMessage.builder(eventName)
        .userId(mapboxUsername));
    }

    if (keyForPropertiesHashMap != null) {

      HashMap<String, String> properties = new HashMap<>();
      properties.put(keyForPropertiesHashMap, valueForPropertiesHashMap);

      analytics.enqueue(TrackMessage.builder(eventName)
        .userId(mapboxUsername)
        .properties(properties));
    }

  }

  public void viewedScreen(AppCompatActivity activity) {

    analytics.enqueue(ScreenMessage.builder(activity.getClass().getSimpleName())
      .userId(mapboxUsername)
    );

  }

  public void identifyUser(@NonNull String actualNameOfUser, @NonNull String userEmailAddress) {

    HashMap<String, String> traits = new HashMap<>();
    traits.put("name", actualNameOfUser);
    traits.put("email", userEmailAddress);

    analytics.enqueue(IdentifyMessage.builder()
      .userId(mapboxUsername)
      .traits(traits)
    );

  }

  public String sendUserLocation() {
//    TODO: Need to finish this method


    return null;
  }

}