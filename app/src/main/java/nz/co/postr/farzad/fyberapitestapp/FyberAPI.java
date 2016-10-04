package nz.co.postr.farzad.fyberapitestapp;

import android.content.Context;
import android.util.Log;

/**
 * Created by farzad on 2/10/16.
 */
public class FyberAPI implements IGetId {
    private static final String TAG = MainActivity.class.getName();
    private static final String APP_ID = "";
    private static final String USER_ID = "";
    private static final String SECURITY_TOKEN = "";
    private static final String CAMPAIGN = "campaign01";
    private static final String API_KEY = "";
    private static final String LOCALE = "en";
    private static final String IP = "";
    private static String GID = "";
    private static String GOOGLE_AD_ID_LIMITED_TRACKING_ENABLED = "false";
    private static String BASIC_URL = "";
    private static long Timestamp = 0;
    Context mContext;

    public FyberAPI(Context mContext) {
        this.mContext = mContext;
    }

    private String makeBasicUrl() {
        return  "appid=" + APP_ID +
                "&device_id=" + FUtil.getAndroidId(mContext) +
                "&google_ad_id=" + GID +
                "&google_ad_id_limited_tracking_enabled=" + GOOGLE_AD_ID_LIMITED_TRACKING_ENABLED +
                "&ip=" + IP +
                "&locale=" + LOCALE +
                "&offer_types=" + "112" +
//                "&ps_time=" + "" +
                "&pub0=" + CAMPAIGN +
                "&timestamp=" + Timestamp +
                "&uid=" + USER_ID;
    }

    public String makeGetRequest() {

//        if (GID == null) {
        requestId();
        Timestamp = FUtil.getCurrentLinuxTimeStamp();
//        }
        String url = makeURL();
        Log.d(TAG, "MakeGetRequest: ----> " + url);
        return url;

    }

    private String makeURL() {
        String url =
                "http://api.fyber.com/feed/v1/offers.json?" +
//                "appid=" + APP_ID +
//                "&device_id=" + FUtil.getAndroidId(mContext) +
//                "&google_ad_id=" + GID +
//                "&google_ad_id_limited_tracking_enabled=" + GOOGLE_AD_ID_LIMITED_TRACKING_ENABLED +
//                "&ip=" + IP +
//                "&locale=" + LOCALE +
//                "&offer_types=" + "112" +
////                "&ps_time=" + "" +
//                "&pub0=" + CAMPAIGN +
//                "&timestamp=" + Timestamp +
//                "&uid=" + USER_ID +
                makeBasicUrl() +
                "&hashkey=" + getHashkey();
        Log.d(TAG, "makeURL: " + url);


        return url;
    }

    private String getHashkey() {
        String toMakeHashStr =
//                "appid=" + APP_ID +
//                "&device_id=" + FUtil.getAndroidId(mContext) +
//                "&google_ad_id=" + GID +
//                "&google_ad_id_limited_tracking_enabled=" + GOOGLE_AD_ID_LIMITED_TRACKING_ENABLED +
//                "&ip=" + IP +
//                "&locale=" + LOCALE +
//                "&offer_types=" + "112" +
////                "&ps_time=" + FUtil.getCurrentLinuxTimeStamp() +
//                "&pub0=" + CAMPAIGN +
//                "&timestamp=" + Timestamp +
//                "&uid=" + USER_ID +
                makeBasicUrl() +
                        "&" + API_KEY;
        Log.d(TAG, "getHashkey: toMakeHashStr ----> " + toMakeHashStr);
        String hash = FUtil.makeSha1(toMakeHashStr);
//        String hash = DigestUtils.sha1Hex(toMakeHashStr);
        Log.d(TAG, "getHashkey: hash ----> " + hash);

        return hash;
    }

    private void requestId() {
        new GetID(mContext, FyberAPI.this).execute();
    }


    @Override
    public void callback(String[] Ids) {
        if (Ids != null && Ids.length > 1) {
            Log.d(TAG, "callback: GID ---->" + Ids[0]);
            GID = Ids[0];
            Log.d(TAG, "callback: GOOGLE_AD_ID_LIMITED_TRACKING_ENABLED ---->" + Ids[1]);
            GOOGLE_AD_ID_LIMITED_TRACKING_ENABLED = Ids[1];
        }
    }


}
