package tetris.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tetris.com.utils.Utils;

/**
 * Created by Ha Anh on 5/4/2018.
 */

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder> {
    private final Context context;
    private final  List<HighScoreObject> values;
    private boolean isShowRank;

    public List<HighScoreObject> getValues() {
        return values;
    }

    public HighScoreAdapter(Context context, List<HighScoreObject> pValue, boolean isShowRanking) {
        this.context = context;
        this.values = pValue;
        this.isShowRank = isShowRanking;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_item_lay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*if(!TextUtils.isEmpty(values.get(position).getStt()))
            holder.stt.setText(values.get(position).getStt());*/
        if(position == 0){
            holder.stt.setTextColor(context.getResources().getColor(R.color.level0));
            holder.name.setTextColor(context.getResources().getColor(R.color.level0));
            holder.score.setTextColor(context.getResources().getColor(R.color.level0));
        }else if(position == 1){
            holder.stt.setTextColor(context.getResources().getColor(R.color.level1));
            holder.name.setTextColor(context.getResources().getColor(R.color.level1));
            holder.score.setTextColor(context.getResources().getColor(R.color.level1));
        }else if(position == 2){
            holder.stt.setTextColor(context.getResources().getColor(R.color.level2));
            holder.name.setTextColor(context.getResources().getColor(R.color.level2));
            holder.score.setTextColor(context.getResources().getColor(R.color.level2));
        }else{
            holder.stt.setTextColor(context.getResources().getColor(R.color.level3));
            holder.name.setTextColor(context.getResources().getColor(R.color.level3));
            holder.score.setTextColor(context.getResources().getColor(R.color.level3));
        }

        if(isShowRank){
            holder.stt.setText(String.valueOf(position + 4));
        }else{
            holder.stt.setText(String.valueOf(position +1));
        }

        if(!TextUtils.isEmpty(values.get(position).getScore()))
            holder.score.setText(values.get(position).getScore());

        if(!TextUtils.isEmpty(values.get(position).getName()))
            holder.name.setText(values.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(values != null && values.size() > 0){
            return values.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView stt, name, score;
        public ViewHolder(View itemView) {
            super(itemView);
            stt     = (TextView) itemView.findViewById(R.id.number_stt_id);
            score   = (TextView) itemView.findViewById(R.id.number_score_id);
            name    = (TextView) itemView.findViewById(R.id.number_name_id);
            Utils.setTypefaceGameOver(context, stt);
            Utils.setTypefaceGameOver(context, name);
            Utils.setTypefaceGameOver(context, score);
        }
    }
}