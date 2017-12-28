package test.com.jianyue.reader_activity.Read_activity.Bottom_list;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.com.jianyue.DataBase.MyOpenHelper;
import test.com.jianyue.R;

public class FavoriteActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public ActionBar actionBar;
    public ListView listView;
    protected List<Articles> articlesList = new ArrayList<Articles>();
    public MyOpenHelper mOpenHelper;
    public SQLiteDatabase db;
    public MyAdapter myAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
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
        listView=findViewById(R.id.listView);
        //initArticles();
        // 创建MyOpenHelper实例
        mOpenHelper = new MyOpenHelper(this);
        // 得到数据库
        db = mOpenHelper.getWritableDatabase();
        // 查询数据
        Query();
        myAdapter= new MyAdapter(FavoriteActivity.this);
        listView.setAdapter(myAdapter);
    }

    // 创建MyAdapter继承BaseAdapter
    class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;

        private MyAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return articlesList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 从articlesList取出Person
            Articles p = articlesList.get(position);
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.article_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.article_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //向TextView中插入数据
            viewHolder.title.setText(p.getTitle());
            return convertView;
        }
    }
    private class ViewHolder {
        private TextView title;
    }

    // 查询数据
    public void Query() {
        Cursor cursor = db.query("Articles", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title1 = cursor.getString(0);
            String author1 = cursor.getString(1);
            String context1 = cursor.getString(2);
            Articles articles = new Articles(title1, author1, context1);
            articlesList.add(articles);
        }
    }
//    //初始化收藏夹数据
//    private void initArticles() {
//        Articles meiwen = new Articles();
//        articlesList.add(meiwen);
//    }
}
