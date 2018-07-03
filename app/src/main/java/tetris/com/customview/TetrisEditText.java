package tetris.com.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import tetris.com.R;
import tetris.com.utils.MetricUtils;
import tetris.com.utils.Utils;

/**
 * Created by DuyAnh on 5/21/2018.
 */

@SuppressLint("AppCompatCustomView")
public class TetrisEditText extends EditText {
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
                R.styleable.TetrisEditText_fontSize, R.styleable.TetrisEditText_fontSizeSystem, R.styleable.TetrisEditText_fontSizeScale);
    }
}