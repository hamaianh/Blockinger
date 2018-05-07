package hamaianh.online.com;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ha Anh on 5/4/2018.
 */

public class HighScoreObject implements Parcelable {
    private String name;
    private String score;

    public HighScoreObject(){
        super();
    }

    public HighScoreObject(String pName, String pScore){
        this.name = pName;
        this.score = pScore;
    }

    protected HighScoreObject(Parcel in) {
        name = in.readString();
        score = in.readString();
    }

    public static final Creator<HighScoreObject> CREATOR = new Creator<HighScoreObject>() {
        @Override
        public HighScoreObject createFromParcel(Parcel in) {
            return new HighScoreObject(in);
        }

        @Override
        public HighScoreObject[] newArray(int size) {
            return new HighScoreObject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(score);
    }
}
