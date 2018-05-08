package hamaianh.online.com.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import hamaianh.online.com.R;
import hamaianh.online.com.utils.Utils;

/**
 * Created by Ha Anh on 5/7/2018.
 */

public class SplashScreenActivity extends Activity{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_lay);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "fonts/UltimateGameplayer-Pixel.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "fonts/game_over.ttf");
        ((TextView) findViewById(R.id.splash_name_game_id)).setTypeface(tf1);
        ((TextView)findViewById(R.id.splash_name_2018_id)).setTypeface(tf2);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
