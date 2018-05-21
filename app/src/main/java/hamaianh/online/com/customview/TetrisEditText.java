package hamaianh.online.com.customview;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import hamaianh.online.com.R;
import hamaianh.online.com.utils.MetricUtils;
import hamaianh.online.com.utils.Utils;

/**
 * Created by DuyAnh on 5/21/2018.
 */

public class TetrisEditText extends AppCompatEditText {
    public TetrisEditText(Context context, TetrisTextView.FONT pFont) {
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

    public TetrisEditText(Context context) {
        super(context);
    }

    public TetrisEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.TetrisEditText, R.styleable.TetrisEditText_fontTypeFaceName,
                R.styleable.TetrisEditText_fontSize, R.styleable.TetrisEditText_fontSizeSystem, R.styleable.TetrisEditText_fontSizeScale);
    }

    public TetrisEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        MetricUtils.setCustomFontAble(this, context, attrs,
                R.styleable.TetrisEditText, R.styleable.TetrisEditText_fontTypeFaceName,
                R.styleable.TetrisEditText_fontSize, R.styleable.TetrisEditText_fontSizeSystem, R.styleable.TetrisTextView_fontSizeScale);
    }
}