package nz.co.postr.farzad.fyberapitestapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

/**
 * Created by farzad on 29/09/16.
 */
public class GetID extends AsyncTask<Void, Void, String[]>{
    private Context mContext;
    private IGetId callbackListener;
    public GetID(Context context){
        this.mContext=context;
    }
    private static final String TAG = MainActivity.class.getName();

    public GetID(Context mContext, IGetId callbackListener) {
        this.mContext = mContext;
        this.callbackListener = callbackListener;
    }

    @Override
        protected String[] doInBackground(Void... params) {
            String[] result =null;
            String googleId = null;
            boolean google_ad_id_limited_tracking_enabled = false;

            try {
                googleId = AdvertisingIdClient.getAdvertisingIdInfo(mContext).getId();
                google_ad_id_limited_tracking_enabled = AdvertisingIdClient.getAdvertisingIdInfo(mContext).isLimitAdTrackingEnabled();
                result = new String[2];
                result[0]= googleId;
                result[1] = ""+google_ad_id_limited_tracking_enabled;
                Log.d(TAG, "----->google Id: "+ googleId);
                Log.d(TAG, "----->google_ad_id_limited_tracking_enabled: "+ google_ad_id_limited_tracking_enabled);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String[] result) {
            Log.i(TAG, "Access token retrieved:" + result);
            callbackListener.callback(result);
        }


}
