package test.com.jianyue.reader_activity.Read_activity.Bottom_list;

import java.io.Serializable;

/**
 * Created by 86758 on 2017/12/28 0028.
 */


public class Articles implements Serializable {
    private int id;
    private String Title;
    private String Author;
    private String Content;

    public Articles(String Title,String Author,String Content){
        super();
        this.Title=Title;
        this.Author=Author;
        this.Content=Content;
    }
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}

