package hamaianh.online.com.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Ha Anh on 5/8/2018.
 */

public class Utils {
    public static void setTypefaceGameplayerPixel(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = Typeface.createFromAsset(context.getAssets(),
                "fonts/UltimateGameplayer-Pixel.ttf");
        if (font != null && views != null && views.length > 0) {
            for (View view : views) {
                if (view instanceof TextView) {
                    ((TextView) view).setTypeface(font);
                } else if (view instanceof EditText) {
                    ((EditText) view).setTypeface(font);
                } else if (view instanceof Button) {
                    ((Button) view).setTypeface(font);
                }
            }
        }
    }

    public static void setTypefaceGameplayer(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = Typeface.createFromAsset(context.getAssets(),
                "fonts/UltimateGameplayer.ttf");
        if (font != null && views != null && views.length > 0) {
            for (View view : views) {
                if (view instanceof TextView) {
                    ((TextView) view).setTypeface(font);
                } else if (view instanceof EditText) {
                    ((EditText) view).setTypeface(font);
                } else if (view instanceof Button) {
                    ((Button) view).setTypeface(font);
                }
            }
        }
    }

    public static void setTypefaceGameOver(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = Typeface.createFromAsset(context.getAssets(),
                "fonts/game_over.ttf");
        if (font != null && views != null && views.length > 0) {
            for (View view : views) {
                if (view instanceof TextView) {
                    ((TextView) view).setTypeface(font);
                } else if (view instanceof EditText) {
                    ((EditText) view).setTypeface(font);
                } else if (view instanceof Button) {
                    ((Button) view).setTypeface(font);
                }
            }
        }
    }
}
