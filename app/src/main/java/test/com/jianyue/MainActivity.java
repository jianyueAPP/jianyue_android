//阅读器主体

package test.com.jianyue;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**test**/


    /**解析 Gson 用到的变量**/
    String text;
    String Title;
    String Auther;
    String Text;
    List<String> list;
    String LJson;

    /**okhttp**/
    public static final String TAG = "MainActivity";
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    String jsonTags = "{\"tag\":[\"ccc\",\"ddd\" ]}";

    @BindView(R.id.MeiWen1)
    CheckBox MeiWen1;
    @BindView(R.id.QinGan1)
    CheckBox QinGan1;
    @BindView(R.id.LiShi1)
    CheckBox LiShi1;
    @BindView(R.id.ZhenTan1)
    CheckBox ZhenTan1;
    @BindView(R.id.LiZhi1)
    CheckBox LiZhi1;
    @BindView(R.id.YouMo1)
    CheckBox YouMo1;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.textView)
    TextView textView;


    private DrawerLayout mDrawerLayout;
    private ScrollView scrollView;
    private TextView bt_settings;
    Toolbar toolbar;
    public float textSize=7;

    public static final String DIALOG_TAG_2 = "dialog2";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开启一个线程，做联网操作
        ButterKnife.bind(this);
        //绑定布局和按键
        mDrawerLayout = findViewById(R.id.drawer_layout);
        scrollView=findViewById(R.id.scrollView);
        bt_settings=findViewById(R.id.setting);
        toolbar = findViewById(R.id.toolbar);//toolbar导入
        setSupportActionBar(toolbar);//toolbar绑定为actionbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//把返回键显示出来
            actionBar.setHomeAsUpIndicator(R.drawable.ic_tag);//把返回键和标签按钮绑定
        }
        //初始化样式
        init();
        //调用按键设置
        set_checkout();
        //点击更多设置按钮
        bt_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawers();
                Dialog_adjust dialog_adjust = Dialog_adjust.newInstance();
                dialog_adjust.setXxxlistener(new Dialog_adjust.xxxlistener() {
                    @Override
                    public void test(int i) {
                        setsize(i);
                    }
                    public void color(int i) {
                        setColor(i);
                    }
                });
                Bottom_Dialog bottom_dialog = Bottom_Dialog.newInstance();
                bottom_dialog.Init(dialog_adjust);
                bottom_dialog.show(getFragmentManager(), DIALOG_TAG_2);
            }
        });

    }

    // 改变字号
    public void setsize(int i){
        System.out.println("tests");
        if(i == 0) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 45);
        }
        else if(i == 1) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 55);
        }
        else if(i == 2) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 65);
        }
    }


    public void setColor(int i) {
        System.out.println("Color");
        if(i == 0) {                // white
            textView.setBackgroundColor(Color.parseColor("#ffffff"));
            textView.setTextColor(Color.parseColor("#333333"));
            toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
            toolbar.setTitleTextColor(Color.parseColor("#333333"));
        }
        else if(i == 1) {           // green
            textView.setBackgroundColor(Color.parseColor("#f0fdf0"));
            textView.setTextColor(Color.parseColor("#709a7b"));
            toolbar.setBackgroundColor(Color.parseColor("#f0fdf0"));
            toolbar.setTitleTextColor(Color.parseColor("#709a7b"));
        }
        else if(i == 2) {           // yellow
            textView.setBackgroundColor(Color.parseColor("#f7f7e8"));
            textView.setTextColor(Color.parseColor("#b88940"));
            toolbar.setBackgroundColor(Color.parseColor("#f7f7e8"));
            toolbar.setTitleTextColor(Color.parseColor("#b88940"));
        }
        else if(i == 3) {           // pink
            textView.setBackgroundColor(Color.parseColor("#fff6ef"));
            textView.setTextColor(Color.parseColor("#db7d6d"));
            toolbar.setBackgroundColor(Color.parseColor("#fff6ef"));
            toolbar.setTitleTextColor(Color.parseColor("#db7d6d"));
        }
        else if(i == 4) {           // night
            textView.setBackgroundColor(Color.parseColor("#0d0d0b"));
            textView.setTextColor(Color.parseColor("#5b5952"));
            toolbar.setBackgroundColor(Color.parseColor("#0d0d0b"));
            toolbar.setTitleTextColor(Color.parseColor("#5b5952"));
        }
    }

    //使用 okhttp 网络获取文章的 Json，LJson 为获取到的 Json，需要进一步读取
    private void testjson(){
        try{
            final Request request = new Request.Builder()
                    .url("http://106.14.154.220:8081/jianyue/getArticle.html?json=历史")
                    .get()
                    .build();

            OkHttpClient client = new OkHttpClient();
            Call mcall = client.newCall(request);
            mcall.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    mHandler.obtainMessage(3, null).sendToTarget();

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    LJson = response.body().string();
                    //String json = response.body().string();
                    mHandler.obtainMessage(1, LJson).sendToTarget();
                }
            });
        }catch (Exception e){
            System.out.println("error!");
        }
    }

    Handler mHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(MainActivity.this, "hhh", Toast.LENGTH_SHORT).show();
                    System.out.println("hhh");
                    return true;
                case 1:
                    //Context mContaxt = new ;
                    System.out.println(msg.obj.toString());
                    /*text = LJson;
                    list = GsonRead.getGson(text);//读取 json
                    Title = list.get(0);        // 标题
                    Auther = list.get(1);       // 作者 TODO Add textview write author
                    Text = list.get(2);         // 文章内容
                    toolbar.setTitle(Title);    // 更改 toolbar 显示的标题
                    textView.setText(Text);     // 更改文章内容 TODO 文章结尾标志
                    textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, MainActivity.getResources().getDimensionPixelOffset(R.dimen.text_middle_size));
                    text = "";*/
                    return true;
                default:
                    return false;
            }
        }
    });

    Handler tshandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    System.out.println("hhh");
                    return true;
                case 1:
                    System.out.println("hhh");
                    return true;
                default:
                    return false;
            }
        }
    });

    //加载toolbar布局
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    //处理toolbar点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://侧边栏
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.flash://刷新
                new Thread() {
                    @Override
                    public void run() {
                        if(jsonTags == null) {
                            System.out.println("runFailed");
                        } else {
//                            //postJson();
//                            testjson();
                        }
                    }
                }.start();
                text = Util.getJson(MainActivity.this, "TestJson.json");
                GsonRead gsonRead;
                System.out.println(LJson);
                System.out.println(LJson);
                //text = LJson;
                list = GsonRead.getGson(text);
                Title = list.get(0);
                Auther = list.get(1);
                Text = list.get(2);
                toolbar.setTitle(Title);
                textView.setText(Text);
                textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 50);
                text = "";
                break;
            default:
        }
        return true;
    }

    //读取SharedPreference，赋值给checkbox兴趣标签,复选框按键功能，把复选框的内容记录到shareperference
    public void set_checkout() {
        //读取SharedPreference，赋值给checkbox兴趣标签
        CheckBox meiwen1 =  findViewById(R.id.MeiWen1);
        CheckBox qingan1 =  findViewById(R.id.QinGan1);
        CheckBox zhentan1 =  findViewById(R.id.ZhenTan1);
        CheckBox lishi1 = findViewById(R.id.LiShi1);
        CheckBox lizhi1 =  findViewById(R.id.LiZhi1);
        CheckBox youmo1 = findViewById(R.id.YouMo1);
        
        SharePreference sp = new SharePreference(MainActivity.this);
        boolean flag = sp.getMeiWen();
        meiwen1.setChecked(flag);
        flag = sp.getQinGan();
        qingan1.setChecked(flag);
        flag = sp.getZhenTan();
        zhentan1.setChecked(flag);
        flag = sp.getLiShi();
        lishi1.setChecked(flag);
        flag = sp.getLiZhi();
        lizhi1.setChecked(flag);
        flag = sp.getYouMo();
        youmo1.setChecked(flag);



        //复选框按键功能，把复选框的内容记录到shareperference
        meiwen1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp = new SharePreference(MainActivity.this);
                if (isChecked) {
                    sp.setMeiWenTrue();
                } else {
                    sp.setMeiWenFalse();
                }
            }
        });
        qingan1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp = new SharePreference(MainActivity.this);
                if (isChecked) {
                    sp.setQinGanTrue();
                } else {
                    sp.setQinGanFalse();
                }
            }
        });
        zhentan1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp = new SharePreference(MainActivity.this);
                if (isChecked) {
                    sp.setZhenTanTrue();
                } else {
                    sp.setZhenTanFalse();
                }
            }
        });
        lishi1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp = new SharePreference(MainActivity.this);
                if (isChecked) {
                    sp.setLiShiTrue();
                } else {
                    sp.setLiShiFalse();
                }
            }
        });
        lizhi1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp = new SharePreference(MainActivity.this);
                if (isChecked) {
                    sp.setLiZhiTrue();
                } else {
                    sp.setLiZhiFalse();
                }
            }
        });
        youmo1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                SharePreference sp = new SharePreference(MainActivity.this);
                if (isChecked) {
                    sp.setYouMoTrue();
                } else {
                    sp.setYouMoFalse();
                }
            }
        });

    }

    @OnClick({R.id.toolbar, R.id.MeiWen1, R.id.QinGan1, R.id.LiShi1, R.id.ZhenTan1, R.id.LiZhi1, R.id.YouMo1, R.id.drawer_layout, R.id.scrollView, R.id.textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                Toast.makeText(MainActivity.this, "toolbar", Toast.LENGTH_SHORT);
                break;
            case R.id.MeiWen1:
                break;
            case R.id.QinGan1:
                break;
            case R.id.LiShi1:
                break;
            case R.id.ZhenTan1:
                break;
            case R.id.LiZhi1:
                break;
            case R.id.YouMo1:
                break;
            case R.id.drawer_layout:
                break;
            case R.id.scrollView:
                break;
            case R.id.textView:
                //Toast.makeText(MainActivity.this, "papapa", Toast.LENGTH_SHORT).show();
                //System.out.println("你点击了test");
                break;
        }
    }


    //初始化样式
    public void init(){
        SharePreference sp = new SharePreference(MainActivity.this);
        //设置文字和背景颜色
        if(sp.getNight()){
            textView.setBackgroundColor(Color.parseColor("#0d0d0b"));
            textView.setTextColor(Color.parseColor("#5b5952"));
            toolbar.setBackgroundColor(Color.parseColor("#0d0d0b"));
            toolbar.setTitleTextColor(Color.parseColor("#5b5952"));
        }
        else{
            if(sp.getWhite()){
                textView.setBackgroundColor(Color.parseColor("#ffffff"));
                textView.setTextColor(Color.parseColor("#333333"));
                toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
                toolbar.setTitleTextColor(Color.parseColor("#333333"));
            }
            if(sp.getGreen()){
                textView.setBackgroundColor(Color.parseColor("#f0fdf0"));
                textView.setTextColor(Color.parseColor("#709a7b"));
                toolbar.setBackgroundColor(Color.parseColor("#f0fdf0"));
                toolbar.setTitleTextColor(Color.parseColor("#709a7b"));
            }
            if(sp.getYellow()){
                textView.setBackgroundColor(Color.parseColor("#f7f7e8"));
                textView.setTextColor(Color.parseColor("#b88940"));
                toolbar.setBackgroundColor(Color.parseColor("#f7f7e8"));
                toolbar.setTitleTextColor(Color.parseColor("#b88940"));
            }
            if(sp.getPink()){
                textView.setBackgroundColor(Color.parseColor("#fff6ef"));
                textView.setTextColor(Color.parseColor("#db7d6d"));
                toolbar.setBackgroundColor(Color.parseColor("#fff6ef"));
                toolbar.setTitleTextColor(Color.parseColor("#db7d6d"));
            }
        }
        //设置文字大小
        int i=sp.getSize();//获取字号
        if(i==0){
            textSize=17;//小号
        }
        else if(i==1){
            textSize=20;
        }
        else if(i==2){
            textSize=23;
        }
        textView.setTextSize(textSize);
    }

}
