package test.com.jianyue.reader_activity.Read_activity.Bottom_list;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import test.com.jianyue.R;
import test.com.jianyue.reader_activity.Read_activity.Articles;
import test.com.jianyue.reader_activity.Read_activity.LikesAdapter;
import test.com.jianyue.reader_activity.Read_activity.MainActivity;

public class FavoriteActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public ActionBar actionBar;
    public ListView listView;

    protected List<Articles> articlesList = new ArrayList<Articles>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        listView=findViewById(R.id.listView);
        initArticles();
        LikesAdapter adapter = new LikesAdapter(FavoriteActivity.this, R.layout.article_item, articlesList);
        listView.setAdapter(adapter);
        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);//toolbar绑定为actionbar
        actionBar = getSupportActionBar();//启用toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//把返回键显示出来
            toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorPrimary));
        }
        //返回键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化收藏夹数据
    private void initArticles() {
        Articles meiwen = new Articles();
        articlesList.add(meiwen);
    }
}
