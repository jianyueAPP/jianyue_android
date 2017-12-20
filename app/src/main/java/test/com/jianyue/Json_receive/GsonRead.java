package test.com.jianyue.Json_receive;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王国华 on 2017/11/16.
 */

public class GsonRead {
    public String title;
    public String author;
    public String link;


    public static List getGson(String jsonString) {
        String Title;
        String Author;
        String Text;
        Gson gson = new Gson();
        GsonRead gsonRead = gson.fromJson(jsonString, GsonRead.class);
        Title = gsonRead.title;
        Author = gsonRead.author;
        Text = gsonRead.link;
        System.out.println(Title);
        System.out.println(Text);
        List<String> list = new ArrayList<String>();
        list.add(Title);
        list.add(Author);
        list.add(Text);
        return list;
    }




}
