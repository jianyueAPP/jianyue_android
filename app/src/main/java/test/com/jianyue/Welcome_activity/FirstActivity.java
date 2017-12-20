//第一个引导页

package test.com.jianyue.Welcome_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import test.com.jianyue.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1=(Button) findViewById(R.id.Next);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
