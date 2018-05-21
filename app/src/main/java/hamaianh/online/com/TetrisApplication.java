package hamaianh.online.com;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import hamaianh.online.com.utils.MetricUtils;

/**
 * Created by DuyAnh on 5/21/2018.
 */

public class TetrisApplication extends Application {
    private boolean mIsScreenOn;
    public static volatile TetrisApplication applicationContext;
    public static volatile Handler applicationHandler;
    public float density = 1;

    BroadcastReceiver mScreenBroadcast = new BroadcastReceiver() {
        //When Event is published, onReceive method is called
        @Override
        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_SCREEN_ON.equals(intent.getAction())){
                setIsScreenOn(true);
            } else if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
                setIsScreenOn(false);
            }
        }
    };

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public void init() {
        try {
            applicationHandler = new Handler(applicationContext.getMainLooper());
            density = applicationContext.getResources().getDisplayMetrics().density;
            mIsScreenOn = true;
            registerReceiver(mScreenBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
            registerReceiver(mScreenBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));
            MetricUtils.init(getApplicationContext());
        } catch (NoClassDefFoundError ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        init();
    }

    /* (non-Javadoc)
     * @see android.app.Application#onLowMemory()
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /* (non-Javadoc)
     * @see android.app.Application#onTerminate()
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        //remove contact change from phone book
        unregisterReceiver(mScreenBroadcast);
    }

    /** The m current activity. */
    private Activity mCurrentActivity = null;

    /**
     * Gets the current activity.
     *
     * @return the current activity
     */
    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }

    /**
     * Sets the current foreground activity.
     *
     * @param mCurrentActivity the new current activity
     */
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }


    /**
     * Clear references.
     */
    public void clearCurrentActivity(Activity pCurrentActivity) {
        Activity currActivity = getCurrentActivity();
        if (currActivity != null && currActivity.equals(pCurrentActivity))
            TetrisApplication.applicationContext.setCurrentActivity(null);
    }

    /**
     * @return the mIsScreenOn
     */
    public boolean isIsScreenOn() {
        return mIsScreenOn;
    }

    /**
     * @param mIsScreenOn the mIsScreenOn to set
     */
    public void setIsScreenOn(boolean mIsScreenOn) {
        this.mIsScreenOn = mIsScreenOn;
    }
}