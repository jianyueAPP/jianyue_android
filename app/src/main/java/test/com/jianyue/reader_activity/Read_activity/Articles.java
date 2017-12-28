package test.com.jianyue.reader_activity.Read_activity;

/**
 * Created by Egbert on 2017/12/28.
 */

public class Articles {
    private String Title;
    private String Author;
    private String Content;
    private int tag = 0;

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

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Articles(String title, String author, String content) {
        super();
        this.Author = author;
        this.Content = content;
        this.Title = title;
    }

}
