package com.gu.baselibrary.baseui;

import android.app.Application;
import android.os.StrictMode;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.BuildConfig;
import org.xutils.x;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * Created by Nate on 2015/9/29.
 */
public class BaseApplication extends Application {

    public static BaseApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        enabledStrictMode();
        CustomActivityOnCrash.install(this);
        mInstance = this;
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    /**
     * 开发阶段开启严苛模式
     */
    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
//                    .penaltyDeath() //
                    .build());
        }
    }

    /**
     * @return BaseApplication
     */
    public static BaseApplication getInstance() {
        return mInstance;
    }
}
