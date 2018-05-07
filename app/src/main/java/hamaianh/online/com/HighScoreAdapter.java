package hamaianh.online.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ha Anh on 5/4/2018.
 */

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreAdapter.ViewHolder> {
    private final Context context;
    private final  List<HighScoreObject> values;


    public List<HighScoreObject> getValues() {
        return values;
    }

    public HighScoreAdapter(Context context, List<HighScoreObject> pValue) {
        this.context = context;
        this.values = pValue;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blockinger_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        private TextView name, score;
        public ViewHolder(View itemView) {
            super(itemView);
            score = (TextView) itemView.findViewById(R.id.text1);
            name = (TextView) itemView.findViewById(R.id.text2);
        }
    }
}
