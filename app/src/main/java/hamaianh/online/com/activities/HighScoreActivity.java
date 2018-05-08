package hamaianh.online.com.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_highscore_id);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager iContactListLayoutManager   = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(iContactListLayoutManager);
        mAdapter = new HighScoreAdapter(this, mListHighScore);
        mRecyclerView.setAdapter(mAdapter);
    }

}
