package tetris.com.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import tetris.com.R;
import tetris.com.customview.TetrisButton;
import tetris.com.customview.TetrisEditText;
import tetris.com.customview.TetrisTextView;
import tetris.com.utils.Utils;

/**
 * Created by Ha Anh on 5/10/2018.
 */

public class NewGameDialog extends DialogFragment implements View.OnClickListener{
    private Context mContext;
    private TetrisTextView leveldialogtext;
    private SeekBar leveldialogBar;
    private int startLevel = 0;
    private OnStartClickListener mOnStartClickListener;
    private TetrisEditText mNamePlayer;
    private TetrisButton mPlayGame;

    public static NewGameDialog getInstance(Context pContext) {
        NewGameDialog instance = new NewGameDialog();
        instance.mContext = pContext;
        return instance;
    }

    public OnStartClickListener getOnStartClickListener() {
        return mOnStartClickListener;
    }

    public void setOnStartClickListener(OnStartClickListener mOnStartClickListener) {
        this.mOnStartClickListener = mOnStartClickListener;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if(getDialog() != null && getDialog().getWindow() != null && getActivity() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        updateWidthDialog();
    }

    private void updateWidthDialog(){
        int iDeviceWidth = getDisplayWidth();

        if(getDialog() != null && getDialog().getWindow() != null && getActivity() != null){
            getDialog().getWindow().setLayout((int)(iDeviceWidth * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public int getDisplayWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getActivity().getApplication().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(getDialog() != null && getDialog().getWindow() != null && getActivity() != null){
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        View v = inflater.inflate(R.layout.new_game_dialog_lay, container);

        initView(v);
        return v;
    }

    private void initView(View v) {
        mPlayGame       = v.findViewById(R.id.new_start_button_id);
        mNamePlayer     = v.findViewById(R.id.new_name_player_id);
        leveldialogtext = v.findViewById(R.id.leveldialogleveldisplay);
        leveldialogBar  = v.findViewById(R.id.levelseekbar);
        leveldialogBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                leveldialogtext.setText("" + arg1);
                startLevel = arg1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }
        });

        mPlayGame.setOnClickListener(this);
        Utils.showInputKeyboardDelayed((Activity) mContext, mNamePlayer, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_start_button_id:
                if (mOnStartClickListener != null) {
                    mOnStartClickListener.onClickStart(startLevel, mNamePlayer.getText().toString());
                }
                dismiss();
                break;

            default:
                break;
        }
    }

    public interface OnStartClickListener{
        void onClickStart(int pLevel, String pName);
    }
}