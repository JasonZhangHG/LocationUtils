package com.locationdemo.android;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.UtilsTransActivity;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mAddressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddressView = findViewById(R.id.address);
        PermissionUtils.permission(PermissionConstants.LOCATION)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(UtilsTransActivity activity, PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        LocationUtil.getLocation(new ICallback<LocationBean>() {
                            @Override
                            public void onResult(LocationBean locationBean) {

                                mAddressView.setText("latitude : " + locationBean.getLatitude()
                                        + "\n\nlongitude : " + locationBean.getLongitude()
                                        + "\n\ncountryName : " + locationBean.getCountryName()
                                        + "\n\ncountryCode : " + locationBean.getCountryCode()

                                );
                            }

                            @Override
                            public void onError(Throwable error) {
                                mAddressView.setText("error : " + error);
                            }
                        });
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            PermissionUtils.launchAppDetailsSettings();
                        }
                    }
                }).request();
    }
}