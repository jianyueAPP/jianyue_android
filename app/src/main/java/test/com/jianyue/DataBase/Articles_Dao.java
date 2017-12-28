package test.com.jianyue.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import test.com.jianyue.reader_activity.Read_activity.Bottom_list.Articles;

/**
 * Created by Egbert on 2017/12/29.
 */

public class Articles_Dao {
    private MyOpenHelper helper;
    private Context mContext;
    private SQLiteDatabase db;
    public Articles_Dao(Context context){
        this.mContext = context;
        helper = new MyOpenHelper(context);
    }
    public void addFavorite(Articles articles){
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title",articles.getTitle());
        values.put("Author",articles.getAuthor());
        values.put("Content",articles.getContent());
        db.insert("Articles",null,values);
        values.clear();
        db.close();
        System.out.println("1024");
    }

    public void deleteFavorite (Articles articles) {
        db = helper.getWritableDatabase();
        db.delete("Articles", "Title = ?", new String[]{articles.getTitle()});
    }
}
