package hamaianh.online.com.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import hamaianh.online.com.HighScoreAdapter;
import hamaianh.online.com.HighScoreObject;
import hamaianh.online.com.R;

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
        /* Database Management */
        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        /*adapter = new SimpleCursorAdapter(
                (Context)this,
                R.layout.blockinger_list_item,
                mCursor,
                new String[] {HighscoreOpenHelper.COLUMN_SCORE, HighscoreOpenHelper.COLUMN_PLAYERNAME},
                new int[] {R.id.text1, R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);*/


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_highscore_id);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager iContactListLayoutManager   = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(iContactListLayoutManager);
        mAdapter = new HighScoreAdapter(this, mListHighScore);
        mRecyclerView.setAdapter(mAdapter);
    }

}
