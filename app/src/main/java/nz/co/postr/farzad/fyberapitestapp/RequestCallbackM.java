package nz.co.postr.farzad.fyberapitestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fyber.ads.AdFormat;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;

/**
 * Created by farzad on 27/09/16.
 */
public class RequestCallbackM extends AppCompatActivity implements RequestCallback {
    private static final String TAG = MainActivity.class.getName();
    private static final int OFFERWALL_REQUEST_CODE = 2;

    @Override
    public void onAdAvailable(Intent intent) {
//        startActivityForResult(intent, OFFERWALL_REQUEST_CODE);
        Log.i(TAG, "------>Offers are available");
    }

    @Override
    public void onAdNotAvailable(AdFormat adFormat) {
        Log.i(TAG, "------>No ad available");
    }

    @Override
    public void onRequestError(RequestError requestError) {
        Log.i(TAG, "------>Something went wrong with the request: " + requestError.getDescription());
    }
}
