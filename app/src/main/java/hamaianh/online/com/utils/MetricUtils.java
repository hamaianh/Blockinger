package hamaianh.online.com.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.Random;

import hamaianh.online.com.TetrisApplication;

/**
 * Created by DuyAnh on 5/21/2018.
 */

public class MetricUtils {
    private static final Hashtable<String, Typeface> typefaceCache = new Hashtable<String, Typeface>();
    public static float density = 1;
    public static float scaledDensity = 1;
    private static Context context;
    private static int mScale3xFollowDesign = 3;
    private static float mScaleFontSize = 1;

    static {
        density = Resources.getSystem().getDisplayMetrics().density;
        scaledDensity = Resources.getSystem().getDisplayMetrics().scaledDensity;
        context = TetrisApplication.applicationContext;
    }

    public static void init(Context pContext) {
        context = pContext;
    }

    public static Typeface getTypeface(String assetPath) {
        synchronized (typefaceCache) {
            if (!typefaceCache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), assetPath);
                    typefaceCache.put(assetPath, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return typefaceCache.get(assetPath);
        }
    }

    public static int dpToPx(final int dp) {
        return (int) (dp * density);
    }

    public static float dpToPx(final float dp) {
        return dp * density;
    }

    public static int dpFToPx(final float dp) {
        return Math.round(dp * density);
    }

    public static int pxToDp(final int px) {
        return (int) (px / density);
    }

    public static int pxToDp(final float px) {
        return Math.round(px / density);
    }

    public static int ptToPx(final float ptValue) {
        final float dpValue = ptValue;// * 0.666666666f;
        return Math.round(dpValue * density);
    }

    public static float spToPx(final float sp) {
        return (int) (sp * scaledDensity);
    }

    public static int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    public static int getShadowMetric() {
        float density = Resources.getSystem().getDisplayMetrics().density;
        if (density <= 1.5) return 1;
        else if (density <= 2) return 2;
        else return 3;
    }

    public static int getScreenWidth() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static void setCustomFontAble(View pView, Context ctx, AttributeSet attrs, int[] attributeSet, int fontId, int fontSizeId, int fontSizeSystemId, int fontSizeScale) {
        if (attributeSet == null || attrs == null)
            return;

        final TypedArray typedArray = ctx.obtainStyledAttributes(attrs, attributeSet);
        if (typedArray != null) {
            try {
                String customFont = typedArray.getString(fontId);
                float customFontSize = typedArray.getFloat(fontSizeId, 54);
                boolean customFontSystem = typedArray.getBoolean(fontSizeSystemId, false);
                float customFontSizeScale = typedArray.getFloat(fontSizeScale, 100);
                setCustomFont(pView, customFont);
                setCustomFontSize(ctx, pView, customFontSize, customFontSystem, customFontSizeScale/100);
            } catch (Exception ignore) {
                // Failed for some reason.
            } finally {
                typedArray.recycle();
            }
        }
    }

    public static boolean setCustomFont(View pView, String asset) {
        Typeface tf = null;
        if (TextUtils.isEmpty(asset))
            asset = "fonts/Roboto-Regular.ttf";

        try {
            tf = getTypeface(asset);
            if (pView instanceof TextView) {
                ((TextView) pView).setTypeface(tf);
            } else if (pView instanceof Button) {
                ((Button) pView).setTypeface(tf);
            } else if (pView instanceof EditText) {
                ((EditText) pView).setTypeface(tf);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean setCustomFontSize(Context ctx, View pView, float fontSize, boolean isUsedSystemFont, float customFontSizeScale) {
        double systemFontSize = Utils.getFontSizeSystemScale(ctx);
        if (!isUsedSystemFont)
            systemFontSize = 1;

        try {
            if (mScale3xFollowDesign == 0)
                mScale3xFollowDesign = 3;

            double sizeFont = systemFontSize * fontSize / mScale3xFollowDesign * mScaleFontSize;
            fontSize = customFontSizeScale * ptToPx(Float.parseFloat(String.valueOf(sizeFont)));
            if (pView instanceof TextView) {
                ((TextView) pView).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            } else if (pView instanceof Button) {
                ((Button) pView).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            } else if (pView instanceof EditText) {
                ((EditText) pView).setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}