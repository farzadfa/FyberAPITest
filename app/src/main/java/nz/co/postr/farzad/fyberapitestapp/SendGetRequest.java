package nz.co.postr.farzad.fyberapitestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by farzad on 3/10/16.
 */
public class SendGetRequest extends AsyncTask<String, Void, String> {
    private static final String TAG = MainActivity.class.getName();
    IGetRequest callbackListener = null;

    public SendGetRequest(IGetRequest callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        int code =0;
        try {
            try {
                Log.d(TAG, "doInBackground: ---->"+strings[0]);
//                URL url = new URL("http://www.google.com");
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                code = connection.getResponseCode();
            }
            catch (MalformedURLException e) {
                // new URL() failed
                // ...
            }
            catch (IOException e) {
                // openConnection() failed
                // ...
            }
            return code+"";
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "Access token retrieved:" + result);
        callbackListener.getRequestCallback(result);
    }
}
