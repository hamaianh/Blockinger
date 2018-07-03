package tetris.com.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/game_over.ttf");
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

    public static void setTypefaceGameTetris(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/NEW.ttf");
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

    /**
     * Sets the typeface roboto regular.
     *
     * @param context the context
     * @param views   the views
     */
    public static void setTypefaceRobotoRegular(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = MetricUtils.getTypeface("fonts/Roboto-Regular.ttf");
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

    /**
     * Sets the typeface roboto medium.
     *
     * @param context the context
     * @param views   the views
     */
    public static void setTypefaceRobotoMedium(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = MetricUtils.getTypeface("fonts/Roboto-Medium.ttf");
        if (font != null && views != null && views.length > 0) {
            for (View view : views) {
                if (view instanceof TextView) {
                    ((TextView) view).setTypeface(font);
                } else if (view instanceof EditText) {
                    ((EditText) view).setTypeface(font);
                } else if (view instanceof Button) {
                    ((Button) view).setTypeface(font);
                } else if (view instanceof SwitchCompat) {
                    ((SwitchCompat) view).setTypeface(font);
                }
            }
        }
    }

    /**
     * Sets the typeface roboto bold.
     *
     * @param context the context
     * @param views   the views
     */
    public static void setTypefaceRobotoBold(Context context, View... views) {
        if (null == context)
            return;
        Typeface font = MetricUtils.getTypeface("fonts/Roboto-Bold.ttf");
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

    public static double getFontSizeSystemScale(Context pContext) {
        double iResult = 1.0;
        try {
            iResult = pContext.getResources().getConfiguration().fontScale;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iResult;
    }

    /**
     * Show input keyboard.
     *
     * @param activity the activity
     * @param v        the v
     */
    public static void showInputKeyboard(Activity activity, View v) {
        if (null == activity || null == v)
            return;
        if (v.getVisibility() == View.VISIBLE && activity != null) {
            v.requestFocus();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void setOnTouchImage(final Context mContext, final ImageView img, final int mColorUp, final int mColorDown){
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getActionMasked() == MotionEvent.ACTION_UP){
                    img.setColorFilter(mColorUp, PorterDuff.Mode.SRC_ATOP);
                }else if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    img.setColorFilter(mColorDown, PorterDuff.Mode.SRC_ATOP);
                }
                return false;
            }
        });
    }

    /**
     * Show input keyboard.
     *
     * @param activity  the activity
     * @param editText  the edit text
     * @param timeDelay the time delay
     */
    public static void showInputKeyboardDelayed(final Activity activity, final EditText editText, int timeDelay) {
        if (null == activity || null == editText)
            return;

        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.showInputKeyboard(activity, editText);
                editText.requestFocus();
            }
        }, 100);
    }
}