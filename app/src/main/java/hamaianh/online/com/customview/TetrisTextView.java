package hamaianh.online.com.customview;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import hamaianh.online.com.R;
import hamaianh.online.com.utils.MetricUtils;
import hamaianh.online.com.utils.Utils;

/**
 * Created by DuyAnh on 5/16/2018.
 */

public class TetrisTextView extends AppCompatTextView {
    public enum FONT{
        REGULAR,
        MEDIUM
    }

    public TetrisTextView(Context context,FONT pFont) {
        super(context);
        if (pFont != null){
            switch (pFont){
                case REGULAR:
                    Utils.setTypefaceRobotoRegular(context,this);
                    break;
                case MEDIUM:
                    Utils.setTypefaceRobotoMedium(context,this);
                    break;
                default:
                    break;
            }
        }
    }
    public void updateSelectedFont(boolean isSelected){
        if (isSelected){
            Utils.setTypefaceRobotoMedium(getContext(),this);
        } else {
            Utils.setTypefaceRobotoRegular(getContext(),this);
        }
    }
    public void setTextAppearance(Context context, int resId) {
        if (Build.VERSION.SDK_INT < 23)
            super.setTextAppearance(context, resId);
        else
            super.setTextAppearance(resId);
    }
    public TetrisTextView(Context context) {
        super(context);
    }

    public TetrisTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.TetrisTextView, R.styleable.TetrisTextView_fontTypeFaceName,
                R.styleable.TetrisTextView_fontSize, R.styleable.TetrisTextView_fontSizeSystem, R.styleable.TetrisTextView_fontSizeScale);
    }

    public TetrisTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.TetrisTextView, R.styleable.TetrisTextView_fontTypeFaceName,
                R.styleable.TetrisTextView_fontSize, R.styleable.TetrisTextView_fontSizeSystem, R.styleable.TetrisTextView_fontSizeScale);
    }
}