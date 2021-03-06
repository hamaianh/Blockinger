/*
 * Copyright 2013 Simon Willeke
 * contact: hamstercount@hotmail.com
 */

/*
    This file is part of Blockinger.

    Blockinger is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Blockinger is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Blockinger.  If not, see <http://www.gnu.org/licenses/>.

    Diese Datei ist Teil von Blockinger.

    Blockinger ist Freie Software: Sie k�nnen es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Option) jeder sp�teren
    ver�ffentlichten Version, weiterverbreiten und/oder modifizieren.

    Blockinger wird in der Hoffnung, dass es n�tzlich sein wird, aber
    OHNE JEDE GEW�HELEISTUNG, bereitgestellt; sogar ohne die implizite
    Gew�hrleistung der MARKTF�HIGKEIT oder EIGNUNG F�R EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License f�r weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
 */

package tetris.com.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

import tetris.com.R;
import io.fabric.sdk.android.Fabric;
import tetris.com.HighScoreObject;
import tetris.com.components.GameState;
import tetris.com.components.Sound;
import tetris.com.db.HighscoreOpenHelper;
import tetris.com.db.ScoreDataSource;
import tetris.com.dialog.NewGameDialog;
import tetris.com.utils.Utils;

public class MainActivity extends Activity implements View.OnClickListener{

	public static final int SCORE_REQUEST = 0x0;

	/** This key is used to access the player name, which is returned as an Intent from the gameactivity upon completion (gameover).
	 *  The Package Prefix is mandatory for Intent data
	 */
	public static final String PLAYERNAME_KEY = "hamaianh.online.com.activities.playername";

	/** This key is used to access the player name, which is returned as an Intent from the gameactivity upon completion (gameover).
	 *  The Package Prefix is mandatory for Intent data
	 */
	public static final String SCORE_KEY = "hamaianh.online.com.activities.score";

	public ScoreDataSource datasource;
	private Sound sound;
	private List<HighScoreObject> mListHighScoreObject;
	private String mNamePlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Fabric.with(this, new Crashlytics());
		setContentView(R.layout.activity_main);
		initView();
		PreferenceManager.setDefaultValues(this, R.xml.simple_preferences, true);
		PreferenceManager.setDefaultValues(this, R.xml.advanced_preferences, true);

		/* Create Music */
		sound = new Sound(this);
		sound.startMusic(Sound.MENU_MUSIC, 0);
		
		/* Database Management */
		Cursor mc;
	    datasource = new ScoreDataSource(this);
	    datasource.open();
	    mc = datasource.getCursor();
		mListHighScoreObject = new ArrayList<HighScoreObject>();
		setListHighScores(mc);

		OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
			@Override
			public void idsAvailable(String userId, String registrationId) {
				String text = "OneSignal UserID:\n" + userId + "\n\n";

				if (registrationId != null)
					text += "Google Registration Id:\n" + registrationId;
				else
					text += "Google Registration Id:\nCould not subscribe for push";

				Log.i("OneSignalExample", text);
//                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initView() {
		ImageView imgHighScore 		= (ImageView)findViewById(R.id.highscoresButton);
		ImageView imgHighSetting	= (ImageView)findViewById(R.id.setting_button_id);
		ImageView imgHighExit 		= (ImageView)findViewById(R.id.exit_button_id);

		Utils.setOnTouchImage(this, imgHighScore, getResources().getColor(R.color.darkbluegreen), getResources().getColor(R.color.yellow));
		Utils.setOnTouchImage(this, imgHighSetting, getResources().getColor(R.color.darkbluegreen), getResources().getColor(R.color.yellow));
		Utils.setOnTouchImage(this, imgHighExit, getResources().getColor(R.color.darkbluegreen), getResources().getColor(R.color.yellow));

		findViewById(R.id.restartButton).setOnClickListener(this);
		findViewById(R.id.resumeButton).setOnClickListener(this);
	}

	private void setListHighScores(Cursor pCursor){
		mListHighScoreObject = new ArrayList<>();
		while(pCursor.moveToNext()) {
			mListHighScoreObject.add(new HighScoreObject(pCursor.getString(pCursor.getColumnIndex(HighscoreOpenHelper.COLUMN_PLAYERNAME)),
					pCursor.getString(pCursor.getColumnIndex(HighscoreOpenHelper.COLUMN_SCORE))));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void start(int pLevel) {
		Intent intent = new Intent(this, GameActivity.class);
		Bundle b = new Bundle();
		b.putInt("mode", GameActivity.NEW_GAME); //Your id
		b.putInt("level", pLevel); //Your id
		b.putString("playername", mNamePlayer); //Your id
		intent.putExtras(b); //Put your id to your next Intent
		startActivityForResult(intent,SCORE_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode != SCORE_REQUEST)
			return;
		if(resultCode != RESULT_OK)
			return;

		String playerName = data.getStringExtra(PLAYERNAME_KEY);
		long score = data.getLongExtra(SCORE_KEY,0);

	    datasource.open();
	    datasource.createScore(score, playerName);
		setListHighScores(datasource.getCursor());
	}

    public void onClickStart(View view) {
		NewGameDialog dialog = NewGameDialog.getInstance(this);
		dialog.setOnStartClickListener(new NewGameDialog.OnStartClickListener() {
			@Override
			public void onClickStart(int pLevel, String pName) {
				mNamePlayer = pName;
				MainActivity.this.start(pLevel);
			}
		});
		dialog.show(getFragmentManager(), "dialog");
    }

    public void onClickResume(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		Bundle b = new Bundle();
		b.putInt("mode", GameActivity.RESUME_GAME); //Your id
		b.putString("playername", mNamePlayer); //Your id
		intent.putExtras(b); //Put your id to your next Intent
		startActivityForResult(intent,SCORE_REQUEST);
    }

    public void onClickHighScore(View view){
		Intent intent = new Intent(this, HighScoreActivity.class);
		Bundle b = new Bundle();
		b.putParcelableArrayList("list_highscore", (ArrayList<? extends Parcelable>) mListHighScoreObject); //Your id
		intent.putExtras(b); //Put your id to your next Intent
		startActivityForResult(intent,SCORE_REQUEST);
	}

	public void onClickSetting(View view){
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	public void onClickExit(View view){
		GameState.destroy();
		MainActivity.this.finish();
	}

	public void onClickHelp(View view){
		Intent intent2 = new Intent(this, HelpActivity.class);
		startActivity(intent2);
	}

	/*public void onClickDonate(View view){
		donateDialog.show();
	}*/

    @Override
    protected void onPause() {
    	super.onPause();
    	sound.pause();
    	sound.setInactive(true);
    };

    @Override
    protected void onStop() {
    	super.onStop();
    	sound.pause();
    	sound.setInactive(true);
    	datasource.close();
    };

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	sound.release();
    	sound = null;
    	datasource.close();
    };

    @Override
    protected void onResume() {
    	super.onResume();
    	sound.setInactive(false);
    	sound.resume();
    	datasource.open();
	    /*Cursor cursor = datasource.getCursor();
	    adapter.changeCursor(cursor);*/

	    if(!GameState.isFinished()) {
			((TextView)findViewById(R.id.resumeButton)).setVisibility(View.VISIBLE);
	    	((TextView)findViewById(R.id.resumeButton)).setEnabled(true);
	    	((TextView)findViewById(R.id.resumeButton)).setTextColor(getResources().getColor(R.color.square_error));
			Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation_ani);
			((TextView)findViewById(R.id.resumeButton)).setAnimation(animation);
	    } else {
			((TextView)findViewById(R.id.resumeButton)).setVisibility(View.GONE);
	    	((TextView)findViewById(R.id.resumeButton)).setEnabled(false);
	    	((TextView)findViewById(R.id.resumeButton)).setTextColor(getResources().getColor(R.color.holo_grey));
	    }
    };

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.restartButton:
				onClickStart(view);
				break;

			case R.id.resumeButton:
				onClickResume(view);
				break;
		}
	}
}