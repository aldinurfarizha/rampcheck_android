package com.riyan.rampcheck.Util;

public class URLs {

  public static final String SERVER_ADDRESS="http://192.168.2.78/";
  public static final String APP_NAME="rampcheck/";
  public static final String API_PATH="api/";
  private static final String FULL_API_URL = SERVER_ADDRESS+APP_NAME+API_PATH;

  //SubRoot URL
  public static final String LOGIN= FULL_API_URL + "login";
  public static final String DASHBOARD=FULL_API_URL+"dashboard";
  public static final String ALL_SOPIR=FULL_API_URL+"getAllSopir";
  public static final String ALL_DATA_CHECK= FULL_API_URL + "getAllCheck";
  public static final String DETAIL_CHECK=FULL_API_URL+"detailCheckWebView/";
  public static final String SCAN_BARCODE=FULL_API_URL+"scanBarcode";
  public static final String INSERT_RAMPCHECK=FULL_API_URL+"insertRampcheck";
  public static final String WEB_HOOK="https://webhook.site/dee03713-f809-474d-bf9b-040962e44310";
}
