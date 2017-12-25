//第三个引导页

package test.com.jianyue.Welcome_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import test.com.jianyue.reader_activity.Read_activity.MainActivity;
import test.com.jianyue.R;
import test.com.jianyue.reader_activity.Read_activity.SharePreference;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.biao_qian);
        //获取控件对象
        CheckBox meiwen=(CheckBox)findViewById(R.id.MeiWen);
        CheckBox qingan=(CheckBox)findViewById(R.id.QinGan);
        CheckBox zhentan=(CheckBox)findViewById(R.id.ZhenTan);
        CheckBox lishi=(CheckBox)findViewById(R.id.LiShi);
        CheckBox lizhi=(CheckBox)findViewById(R.id.LiZhi);
        CheckBox youmo=(CheckBox)findViewById(R.id.YouMo);
        
        Button button2=(Button) findViewById(R.id.Next2);


        //复选框按键功能，把复选框的内容记录到shareperference
        meiwen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp=new SharePreference(ThirdActivity.this);
                if(isChecked){
                    sp.setMeiWenTrue();
                }
                else{
                    sp.setMeiWenFalse();
                }
            }
        });
        qingan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp=new SharePreference(ThirdActivity.this);
                if(isChecked){
                    sp.setQinGanTrue();
                }
                else{
                    sp.setQinGanFalse();
                }
            }
        });
        zhentan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp=new SharePreference(ThirdActivity.this);
                if(isChecked){
                    sp.setZhenTanTrue();
                }
                else{
                    sp.setZhenTanFalse();
                }
            }
        });
        lishi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp=new SharePreference(ThirdActivity.this);
                if(isChecked){
                    sp.setLiShiTrue();
                }
                else{
                    sp.setLiShiFalse();
                }
            }
        });
        lizhi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp=new SharePreference(ThirdActivity.this);
                if(isChecked){
                    sp.setLiZhiTrue();
                }
                else{
                    sp.setLiZhiFalse();
                }
            }
        });
        youmo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp=new SharePreference(ThirdActivity.this);
                if(isChecked){
                    sp.setYouMoTrue();
                }
                else{
                    sp.setYouMoFalse();
                }
            }
        });

        //next2按钮功能，调到阅读器主体界面
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharePreference sp=new SharePreference(ThirdActivity.this);
                sp.setState();  //将登陆状态设置为true
                init(); //初始化后面所用到的按键状态
                Intent intent=new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //初始化后面所用到的按键状态
    public void init(){
        SharePreference sp=new SharePreference(ThirdActivity.this);
        sp.setYellowTrue();
        sp.setWhiteFalse();
        sp.setGreenFalse();
        sp.setPinkFalse();
    }
}
