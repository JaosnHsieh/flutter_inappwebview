package com.pichillilorenzo.flutter_inappwebview.in_app_browser;

import androidx.annotation.Nullable;

import com.pichillilorenzo.flutter_inappwebview.ISettings;
import com.pichillilorenzo.flutter_inappwebview.R;

import java.util.HashMap;
import java.util.Map;

public class InAppBrowserSettings implements ISettings<InAppBrowserActivity> {

  public static final String LOG_TAG = "InAppBrowserSettings";

  public Boolean hidden = false;
  public Boolean hideToolbarTop = false;
  @Nullable
  public String toolbarTopBackgroundColor;
  @Nullable
  public String toolbarTopFixedTitle;
  public Boolean hideUrlBar = false;
  public Boolean hideProgressBar = false;

  public Boolean hideTitleBar = false;
  public Boolean closeOnCannotGoBack = true;
  public Boolean allowGoBackWithBackButton = true;
  public Boolean shouldCloseOnBackButtonPressed = false;

  @Override
  public InAppBrowserSettings parse(Map<String, Object> settings) {
    for (Map.Entry<String, Object> pair : settings.entrySet()) {
      String key = pair.getKey();
      Object value = pair.getValue();
      if (value == null) {
        continue;
      }

      switch (key) {
        case "hidden":
          hidden = (Boolean) value;
          break;
        case "hideToolbarTop":
          hideToolbarTop = (Boolean) value;
          break;
        case "toolbarTopBackgroundColor":
          toolbarTopBackgroundColor = (String) value;
          break;
        case "toolbarTopFixedTitle":
          toolbarTopFixedTitle = (String) value;
          break;
        case "hideUrlBar":
          hideUrlBar = (Boolean) value;
          break;
        case "hideTitleBar":
          hideTitleBar = (Boolean) value;
          break;
        case "closeOnCannotGoBack":
          closeOnCannotGoBack = (Boolean) value;
          break;
        case "hideProgressBar":
          hideProgressBar = (Boolean) value;
          break;
        case "allowGoBackWithBackButton":
          allowGoBackWithBackButton = (Boolean) value;
          break;
        case "shouldCloseOnBackButtonPressed":
          shouldCloseOnBackButtonPressed = (Boolean) value;
          break;
      }
    }

    return this;
  }

  @Override
  public Map<String, Object> toMap() {
    Map<String, Object> settings = new HashMap<>();
    settings.put("hidden", hidden);
    settings.put("hideToolbarTop", hideToolbarTop);
    settings.put("toolbarTopBackgroundColor", toolbarTopBackgroundColor);
    settings.put("toolbarTopFixedTitle", toolbarTopFixedTitle);
    settings.put("hideUrlBar", hideUrlBar);
    settings.put("hideTitleBar", hideTitleBar);
    settings.put("closeOnCannotGoBack", closeOnCannotGoBack);
    settings.put("hideProgressBar", hideProgressBar);
    settings.put("allowGoBackWithBackButton", allowGoBackWithBackButton);
    settings.put("shouldCloseOnBackButtonPressed", shouldCloseOnBackButtonPressed);
    return settings;
  }

  @Override
  public Map<String, Object> getRealSettings(InAppBrowserActivity inAppBrowserActivity) {
    Map<String, Object> realSettings = toMap();
    realSettings.put("hideToolbarTop", !inAppBrowserActivity.actionBar.isShowing());
    realSettings.put("hideUrlBar", !inAppBrowserActivity.menu.findItem(R.id.menu_search).isVisible());
    realSettings.put("hideProgressBar", inAppBrowserActivity.progressBar.getMax() == 0);
    return realSettings;
  }
}