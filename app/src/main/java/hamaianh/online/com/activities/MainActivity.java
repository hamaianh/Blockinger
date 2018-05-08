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

package hamaianh.online.com.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hamaianh.online.com.HighScoreObject;
import hamaianh.online.com.R;
import hamaianh.online.com.components.GameState;
import hamaianh.online.com.components.Sound;
import hamaianh.online.com.db.HighscoreOpenHelper;
import hamaianh.online.com.db.ScoreDataSource;

public class MainActivity extends Activity {

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
	//private SimpleCursorAdapter adapter;
	private AlertDialog.Builder startLevelDialog;
	private AlertDialog.Builder donateDialog;
	private int startLevel;
	private View dialogView;
	private SeekBar leveldialogBar;
	private TextView leveldialogtext;
	private Sound sound;
	private List<HighScoreObject> mListHighScoreObject;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    /*adapter = new SimpleCursorAdapter(
	    	(Context)this,
	        R.layout.blockinger_list_item,
	        mc,
	        new String[] {HighscoreOpenHelper.COLUMN_SCORE, HighscoreOpenHelper.COLUMN_PLAYERNAME},
	        new int[] {R.id.text1, R.id.text2},
	        SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);*/

		mListHighScoreObject = new ArrayList<HighScoreObject>();
		setListHighScores(mc);

	    //setListAdapter(adapter);
	    
	    /* Create Startlevel Dialog */
	    startLevel = 0;
	    startLevelDialog = new AlertDialog.Builder(this);
		startLevelDialog.setTitle(R.string.startLevelDialogTitle);
		startLevelDialog.setCancelable(false);
		startLevelDialog.setNegativeButton(R.string.startLevelDialogCancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		startLevelDialog.setPositiveButton(R.string.startLevelDialogStart, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.start();
			}
		});
	    
		/* Create Donate Dialog */
	    donateDialog = new AlertDialog.Builder(this);
	    donateDialog.setTitle(R.string.pref_donate_title);
	    donateDialog.setMessage(R.string.pref_donate_summary);
	    donateDialog.setNegativeButton(R.string.startLevelDialogCancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	    donateDialog.setPositiveButton(R.string.donate_button, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String url = getResources().getString(R.string.donation_url);
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
				return true;
			case R.id.action_about:
				Intent intent1 = new Intent(this, AboutActivity.class);
				startActivity(intent1);
				return true;
			case R.id.action_donate:
				donateDialog.show();
				return true;
			case R.id.action_help:
				Intent intent2 = new Intent(this, HelpActivity.class);
				startActivity(intent2);
				return true;
			case R.id.action_exit:
			    GameState.destroy();
			    MainActivity.this.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void start() {
		Intent intent = new Intent(this, GameActivity.class);
		Bundle b = new Bundle();
		b.putInt("mode", GameActivity.NEW_GAME); //Your id
		b.putInt("level", startLevel); //Your id
		b.putString("playername", ((TextView)findViewById(R.id.nicknameEditView)).getText().toString()); //Your id
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
		dialogView = getLayoutInflater().inflate(R.layout.seek_bar_dialog, null);
		leveldialogtext = ((TextView)dialogView.findViewById(R.id.leveldialogleveldisplay));
		leveldialogBar = ((SeekBar)dialogView.findViewById(R.id.levelseekbar));
		leveldialogBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
		leveldialogBar.setProgress(startLevel);
		leveldialogtext.setText("" + startLevel);
		startLevelDialog.setView(dialogView);
		startLevelDialog.show();
    }

    public void onClickResume(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		Bundle b = new Bundle();
		b.putInt("mode", GameActivity.RESUME_GAME); //Your id
		b.putString("playername", ((TextView)findViewById(R.id.nicknameEditView)).getText().toString()); //Your id
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

	public void onClickAbout(View view){
		Intent intent1 = new Intent(this, AboutActivity.class);
		startActivity(intent1);
	}

	public void onClickExit(View view){
		GameState.destroy();
		MainActivity.this.finish();
	}

	public void onClickHelp(View view){
		Intent intent2 = new Intent(this, HelpActivity.class);
		startActivity(intent2);
	}

	public void onClickDonate(View view){
		donateDialog.show();
	}
    
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
	    	((Button)findViewById(R.id.resumeButton)).setEnabled(true);
	    	((Button)findViewById(R.id.resumeButton)).setTextColor(getResources().getColor(R.color.square_error));
	    } else {
	    	((Button)findViewById(R.id.resumeButton)).setEnabled(false);
	    	((Button)findViewById(R.id.resumeButton)).setTextColor(getResources().getColor(R.color.holo_grey));
	    }
    };

}
