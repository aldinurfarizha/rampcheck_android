package com.riyan.rampcheck.Util;

public class URLs {

  //Api Key
  public static final String API_KEY = "billman_asik_sehat";
  //public static final String SERVER_ADDRESS="http://pamkuningan.co.id/";
  public static final String SERVER_ADDRESS="http://192.168.2.77/";
  public static final String APP_NAME="rampcheck/";
  public static final String API_PATH="api/";
  private static final String FULL_API_URL = SERVER_ADDRESS+APP_NAME+API_PATH;

  //SubRoot URL
  public static final String LOGIN= FULL_API_URL + "login";
  public static final String DETAIL_INVENTARIS= FULL_API_URL + "detailInventaris";
  public static final String UPDATE_HISTORY_INVENTARIS=FULL_API_URL+"updateHistoryInventaris";
  public static final String UPDATE_FOTO_INVENTARIS=FULL_API_URL+"updateFotoInventaris";
  public static final String DASHBOARD=FULL_API_URL+"dashboard";
}
