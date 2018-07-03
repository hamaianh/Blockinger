package tetris.com.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import tetris.com.R;

/**
 * Created by Ha Anh on 5/10/2018.
 */

public class GameOverDialog extends DialogFragment implements View.OnClickListener{
    private int startLevel = 0;
    private TextView mScore, mAPM, mTime;
    private long mScoreStr;
    private String mAPMStr, mTimeStr;

    public OnBackMenuClickListener getOnBackMenuClickListener() {
        return mOnBackMenuClickListener;
    }

    public void setOnBackMenuClickListener(OnBackMenuClickListener mOnBackMenuClickListener) {
        this.mOnBackMenuClickListener = mOnBackMenuClickListener;
    }

    private OnBackMenuClickListener mOnBackMenuClickListener;
    private Button mBackButton;

    public static GameOverDialog getInstance(long score, String apm, String time){
        GameOverDialog instance = new GameOverDialog();
        instance.mScoreStr = score;
        instance.mAPMStr = apm;
        instance.mTimeStr = time;
        return instance;
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
        View v = inflater.inflate(R.layout.game_over_dialog_lay, container);

        if(getDialog() != null && getDialog().getWindow() != null && getActivity() != null){
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        initView(v);

        return v;
    }

    private void initView(View v) {
        mBackButton = (Button)v.findViewById(R.id.gameover_back_button_id);
        mBackButton.setOnClickListener(this);
        mScore      = (TextView)v.findViewById(R.id.gameover_score_point_id);
        mAPM        = (TextView)v.findViewById(R.id.gameover_apm_point_id);
        mTime       = (TextView)v.findViewById(R.id.gameover_time_id);

        if(!TextUtils.isEmpty(String.valueOf(mScoreStr))){
            mScore.setText(String.valueOf(mScoreStr));
        }

        if(!TextUtils.isEmpty(mAPMStr)){
            mAPM.setText(mAPMStr);
        }
        if(!TextUtils.isEmpty(mTimeStr)){
            mTime.setText(mTimeStr);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gameover_back_button_id:
                if(mOnBackMenuClickListener != null)
                    mOnBackMenuClickListener.onClickBackMenu(mScoreStr);
                break;

                default:
                    break;
        }
    }

    public interface OnBackMenuClickListener {
        void onClickBackMenu(long pScore);
    }
}