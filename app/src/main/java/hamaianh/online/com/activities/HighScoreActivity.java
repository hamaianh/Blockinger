package hamaianh.online.com.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hamaianh.online.com.HighScoreAdapter;
import hamaianh.online.com.HighScoreObject;
import hamaianh.online.com.R;
import hamaianh.online.com.utils.Utils;

/**
 * Created by Ha Anh on 5/4/2018.
 */

public class HighScoreActivity extends Activity{
    private HighScoreAdapter mAdapter;
    private List<HighScoreObject> mListHighScore;
    private RecyclerView mRecyclerView;
    private LinearLayout mRankingLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores_lay);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            if(b.getParcelableArrayList("list_highscore") != null)
                mListHighScore = b.getParcelableArrayList("list_highscore");
        }
        initView();
    }

    private void initView() {
        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.highscores_title_id));
        mRankingLay = (LinearLayout)findViewById(R.id.ranking_lay_id);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_highscore_id);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);
        LinearLayoutManager iContactListLayoutManager   = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(iContactListLayoutManager);
        if(mListHighScore != null && mListHighScore.size() > 3){
            mRankingLay.setVisibility(View.VISIBLE);
            findViewById(R.id.highscores_line_id).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.name_1_id)).setText(mListHighScore.get(0).getName());
            ((TextView)findViewById(R.id.name_2_id)).setText(mListHighScore.get(1).getName());
            ((TextView)findViewById(R.id.name_3_id)).setText(mListHighScore.get(2).getName());
            ((TextView)findViewById(R.id.score_1_id)).setText(mListHighScore.get(0).getScore());
            ((TextView)findViewById(R.id.score_2_id)).setText(mListHighScore.get(1).getScore());
            ((TextView)findViewById(R.id.score_3_id)).setText(mListHighScore.get(2).getScore());
            List<HighScoreObject> mListHighScoreNew = new ArrayList<>();
            for (int i = 3; i < mListHighScore.size(); i++){
                mListHighScoreNew.add(mListHighScore.get(i));
            }
            mAdapter = new HighScoreAdapter(this, mListHighScoreNew, true);
        }else{
            mRankingLay.setVisibility(View.GONE);
            findViewById(R.id.highscores_line_id).setVisibility(View.GONE);
            mAdapter = new HighScoreAdapter(this, mListHighScore, false);
        }
        mRecyclerView.setAdapter(mAdapter);
        ((TextView)findViewById(R.id.name_1_id)).setTextColor(getResources().getColor(R.color.gold));
        ((TextView)findViewById(R.id.name_2_id)).setTextColor(getResources().getColor(R.color.blue));
        ((TextView)findViewById(R.id.name_3_id)).setTextColor(getResources().getColor(R.color.orange));
        ((TextView)findViewById(R.id.score_1_id)).setTextColor(getResources().getColor(R.color.gold));
        ((TextView)findViewById(R.id.score_2_id)).setTextColor(getResources().getColor(R.color.blue));
        ((TextView)findViewById(R.id.score_3_id)).setTextColor(getResources().getColor(R.color.orange));

        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.name_1_id));
        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.name_2_id));
        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.name_3_id));
        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.score_1_id));
        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.score_2_id));
        Utils.setTypefaceGameOver(this, (TextView)findViewById(R.id.score_3_id));

    }

    public void onClickBack(View view){
        HighScoreActivity.this.finish();
    }

}
