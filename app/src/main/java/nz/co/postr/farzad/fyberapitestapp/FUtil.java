package nz.co.postr.farzad.fyberapitestapp;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by farzad on 30/09/16.
 */

public class FUtil implements IGetRequest{
    private static final String TAG = MainActivity.class.getName();
    public static String getAndroidId(Context context) {
        String id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return id;
    }

    public static long getCurrentLinuxTimeStamp() {
        return System.currentTimeMillis() / 1000L;
    }


    public static String makeSha1(String input) {
        String sh1hash = "";
        Log.d(TAG, "makeSha1: ---> URL : "+input);
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            sh1hash = sb.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return sh1hash;
    }

    // HTTP GET request
    public void sendGetRequest(String input)  {
        new SendGetRequest(FUtil.this).execute(input);
    }


    @Override
    public void getRequestCallback(String url) {
        Log.d(TAG, "getRequestCallback: ---->"+url);
    }
}
