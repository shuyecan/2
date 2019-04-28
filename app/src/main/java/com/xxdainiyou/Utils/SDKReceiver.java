package com.xxdainiyou.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;


public class SDKReceiver extends BroadcastReceiver {

    private String TAG = "baidu";
    public void onReceive(Context context, Intent intent) {
        String s = intent.getAction();
        Log.d(TAG, "action: " + s);
        if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
            Log.e(TAG, "key 验证出错! 错误码 :" + intent.getIntExtra
                    (SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, 0)
                    +  " ; 请在 AndroidManifest.xml 文件中检查 key 设置");
        } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
            Log.e(TAG, "key 验证成功! 功能可以正常使用");
        } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
            Log.e(TAG, "网络出错");
        }
    }
}
