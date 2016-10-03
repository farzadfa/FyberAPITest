package nz.co.postr.farzad.fyberapitestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FyberAPI fyberAPI = new FyberAPI(this);
        FUtil fyberUtil = new FUtil();
//        FUtil.sendGet(fyberAPI.makeGetRequest());
//        fyberUtil.sendGetRequest("http://www.google.com");
        fyberUtil.sendGetRequest(fyberAPI.makeGetRequest());

    }

}
