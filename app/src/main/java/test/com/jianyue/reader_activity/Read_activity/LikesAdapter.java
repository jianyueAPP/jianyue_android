package test.com.jianyue.reader_activity.Read_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.com.jianyue.R;

/**
 * Created by Egbert on 2017/12/28.
 */

public class LikesAdapter extends ArrayAdapter<Articles>{
    private int resourseId;
    private ArrayList<Articles> articles = new ArrayList<>();

    public void setArticles(ArrayList<Articles> articles){
        this.articles = articles;
    }

    public LikesAdapter (Context context, int textViewResourseId, List<Articles> objects) {
        super(context, textViewResourseId, objects);
        resourseId = textViewResourseId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Articles article = articles.get(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourseId, null);
        TextView title = (TextView) view.findViewById(R.id.article_title);
        //TextView author = (TextView) view.findViewById(R.id.authorTextView);
        title.setText(article.getTitle());
        return view;
    }


}
