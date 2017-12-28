package test.com.jianyue.reader_activity.Read_activity.Bottom_list;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import test.com.jianyue.R;

public class SuggestActivity extends AppCompatActivity {

    //控件声明
    public Toolbar toolbar1;
    public TextView SendButton;
    public EditText editText;
    public ActionBar actionBar;
    public String message;
    public String link = "http://106.14.154.220:8081/jianyue/getAdvice.html?json=";
    public String URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        SendButton=findViewById(R.id.Send);
        editText=findViewById(R.id.SuggestText);
        toolbar1=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);//toolbar绑定为actionbar
        actionBar = getSupportActionBar();//启用toolbar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//把返回键显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            toolbar1.setTitleTextColor(this.getResources().getColor(R.color.toolbar_back));
        }
        //提交按钮
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message=editText.getText().toString();
                if(message.length()==0){//如果没写建议就发送
                    Toast.makeText(SuggestActivity.this, "反馈不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    //把意见发送给服务器
                    URL = link + message;
                    Advice(URL);
                    Toast.makeText(SuggestActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        //返回键
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    // okhttp 意见反馈
    public void Advice(String URL) {
        try{
            final Request request = new Request.Builder()
                    .url(URL)
                    .get()
                    .build();

            OkHttpClient client = new OkHttpClient();
            Call mcall = client.newCall(request);
            mcall.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    //mHandler.obtainMessage(3, null).sendToTarget();

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    //LJson = response.body().string();
                    //String json = response.body().string();
                    //mHandler.obtainMessage(1, LJson).sendToTarget();
                }
            });
        }catch (Exception e){
            System.out.println("error!");
        }
    }
}
