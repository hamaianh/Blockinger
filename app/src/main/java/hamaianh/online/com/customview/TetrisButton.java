package hamaianh.online.com.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import hamaianh.online.com.R;
import hamaianh.online.com.utils.MetricUtils;
import hamaianh.online.com.utils.Utils;

/**
 * Created by DuyAnh on 5/21/2018.
 */

public class TetrisButton extends AppCompatButton {
    public TetrisButton(Context context, TetrisTextView.FONT pFont) {
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
    public TetrisButton(Context context) {
        super(context);
    }

    public TetrisButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.TetrisButton, R.styleable.TetrisButton_fontTypeFaceName,
                R.styleable.TetrisButton_fontSize, R.styleable.TetrisButton_fontSizeSystem, R.styleable.TetrisButton_fontSizeScale);
    }

    public TetrisButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.TetrisButton, R.styleable.TetrisButton_fontTypeFaceName,
                R.styleable.TetrisButton_fontSize, R.styleable.TetrisButton_fontSizeSystem, R.styleable.TetrisButton_fontSizeScale);
    }
}