package test.com.jianyue;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //让窗口1s后自动消失
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharePreference sp=new SharePreference(WelcomeActivity.this);
                boolean isLogin = sp.getState();
                if(isLogin){//不是第一次启动
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {//第一次启动
                    Intent intent=new Intent(WelcomeActivity.this,FirstActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1000); //1s for release
    }


}
