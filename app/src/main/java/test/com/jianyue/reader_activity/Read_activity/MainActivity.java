//阅读器主体

package test.com.jianyue.reader_activity.Read_activity;


import android.annotation.SuppressLint;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import test.com.jianyue.R;
import test.com.jianyue.Json_receive.GsonRead;
import test.com.jianyue.Json_receive.Util;
import test.com.jianyue.reader_activity.Read_activity.Bottom_list.Bottom_Dialog;
import test.com.jianyue.reader_activity.Read_activity.Bottom_list.Dialog_adjust;

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
    public static final String DIALOG_TAG_2 = "dialog2";
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    String jsonTags = "{\"tag\":[\"ccc\",\"ddd\" ]}";

    @BindView(R.id.textView)
    TextView textView;
    private DrawerLayout mDrawerLayout;
    private ScrollView scrollView;
    private TextView textTitle,textAuthor,barTitle,textFinish;
    private Toolbar toolbar;
    private ActionBar actionBar;
    public float textSize=7;
    public int color=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //调用布局
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //绑定布局和按键
        mDrawerLayout = findViewById(R.id.drawer_layout);
        scrollView=findViewById(R.id.scrollView);
        barTitle=findViewById(R.id.barTitle);
        textTitle=findViewById(R.id.textTitle);
        textAuthor=findViewById(R.id.textAuthor);
        textFinish=findViewById(R.id.textFinish);
        toolbar = findViewById(R.id.toolbar);//toolbar导入
        setSupportActionBar(toolbar);//toolbar绑定为actionbar
        actionBar = getSupportActionBar();//启用toolbar
        //初始化样式
        init();
        //调用按键设置
        set_checkout();
        //OnTouch监听器
        scrollView.setOnTouchListener(new PicOnTouchListener());
    }
    //OnTouch监听器,监听scrollview的滑动，让标题选择显示
    private class PicOnTouchListener implements View.OnTouchListener {
        private int lastY = 0;
        private int touchEventId = -9983761;
        int[] position=new int[2];
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int eventAction = event.getAction();
            switch (eventAction) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    textAuthor.getLocationOnScreen(position);
                    if(position[1]<=150){//作者不在屏幕上
                        barTitle.setText(Title);
                    }
                    else{//作者在屏幕上
                        barTitle.setText("");
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    //惯性滑动，每隔1ms监听一次
                    handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 1);
                    break;
                default:
                    break;
            }
            return false;
        }
        @SuppressLint("HandlerLeak")
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                View scroller = (View) msg.obj;
                if (msg.what == touchEventId) {
                    if (lastY != scroller.getScrollY()) {//窗口惯性滑动未停止
                        textAuthor.getLocationOnScreen(position);
                        if(position[1]<=150){//作者不在屏幕上
                            barTitle.setText(Title);
                        }
                        else{//作者在屏幕上
                            barTitle.setText("");
                        }
                        handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 1);
                        lastY = scroller.getScrollY();
                    }
                }
            }
        };
    }
    //初始化时加载toolbar布局
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        changeFlashButtonColor(menu);
        return true;
    }
    //动态加载刷新按钮，让它随着背景颜色的变化而变化
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        changeFlashButtonColor(menu);//调用改变刷新按钮颜色函数
        return super.onPrepareOptionsMenu(menu);
    }
    //改变刷新按钮颜色函数
    public void changeFlashButtonColor(Menu menu){
        switch (color) {
            case 0:
                menu.findItem(R.id.flashWhite).setVisible(true);
                menu.findItem(R.id.flashGreen).setVisible(false);
                menu.findItem(R.id.flashYellow).setVisible(false);
                menu.findItem(R.id.flashPink).setVisible(false);
                menu.findItem(R.id.flashNight).setVisible(false);
                break;
            case 1:
                menu.findItem(R.id.flashWhite).setVisible(false);
                menu.findItem(R.id.flashGreen).setVisible(true);
                menu.findItem(R.id.flashYellow).setVisible(false);
                menu.findItem(R.id.flashPink).setVisible(false);
                menu.findItem(R.id.flashNight).setVisible(false);
                break;
            case 2:
                menu.findItem(R.id.flashWhite).setVisible(false);
                menu.findItem(R.id.flashGreen).setVisible(false);
                menu.findItem(R.id.flashYellow).setVisible(true);
                menu.findItem(R.id.flashPink).setVisible(false);
                menu.findItem(R.id.flashNight).setVisible(false);
                break;
            case 3:
                menu.findItem(R.id.flashWhite).setVisible(false);
                menu.findItem(R.id.flashGreen).setVisible(false);
                menu.findItem(R.id.flashYellow).setVisible(false);
                menu.findItem(R.id.flashPink).setVisible(true);
                menu.findItem(R.id.flashNight).setVisible(false);
                break;
            case 4:
                menu.findItem(R.id.flashWhite).setVisible(false);
                menu.findItem(R.id.flashGreen).setVisible(false);
                menu.findItem(R.id.flashYellow).setVisible(false);
                menu.findItem(R.id.flashPink).setVisible(false);
                menu.findItem(R.id.flashNight).setVisible(true);
                break;
        }
    }
    //初始化界面样式
    public void init(){
        SharePreference sp = new SharePreference(MainActivity.this);
        //初始化toolbar的标签按钮样式
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//把返回键显示出来
            //根据背景颜色显示toolbar的标签颜色
            if(sp.getNight()){
                actionBar.setHomeAsUpIndicator(R.drawable.ic_tagnight);//把返回键和标签按钮绑定
            }
            else{
                if(sp.getWhite()){
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_tagwhite);
                }
                else if(sp.getGreen()){
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_taggreen);
                }
                else if(sp.getYellow()){
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_tagyellow);
                }
                else if(sp.getPink()){
                    actionBar.setHomeAsUpIndicator(R.drawable.ic_tagpink);
                }
            }
        }
        //设置文字和背景颜色
        if(sp.getNight()){
            color=4;
            setTextColor(4);
        }
        else{
            if(sp.getWhite()){
                color=0;
                setTextColor(0);
            }
            if(sp.getGreen()){
                color=1;
                setTextColor(1);
            }
            if(sp.getYellow()){
                color=2;
                setTextColor(2);
            }
            if(sp.getPink()){
                color=3;
                setTextColor(3);
            }
        }
        //设置toolbar刷新按钮样式
        getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
        invalidateOptionsMenu();
        //初始化文字大小
        int i=sp.getSize();//获取字号
        setsize(i);
    }
    // 设置字体大小
    public void setsize(int i){
        if(i == 0) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 50);
        }
        else if(i == 1) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 55);
        }
        else if(i == 2) {
            textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 63);
        }
    }
    //调用设置字体背景颜色
    public void setColor(int i) {
        color=i;
        //改变toolbar刷新按钮颜色
        getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
        invalidateOptionsMenu();
        if(i == 0) {                // white
            setTextColor(0);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_tagwhite);
        }
        else if(i == 1) {           // green
            setTextColor(1);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_taggreen);
        }
        else if(i == 2) {           // yellow
            setTextColor(2);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_tagyellow);
        }
        else if(i == 3) {           // pink
            setTextColor(3);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_tagpink);
        }
        else if(i == 4) {           // night
            setTextColor(4);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_tagnight);
        }
    }
    //处理toolbar点击事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://侧边栏
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.flashWhite://刷新
                flash_text();
                break;
            case R.id.flashNight://刷新
                flash_text();
                break;
            case R.id.flashGreen://刷新
                flash_text();
                break;
            case R.id.flashPink://刷新
                flash_text();
                break;
            case R.id.flashYellow://刷新
                flash_text();
                break;
            default:
        }
        return true;
    }
    //刷新功能
    public void flash_text(){
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
        scrollView.fullScroll(View.FOCUS_UP);//返回顶部
        barTitle.setText("");
        textTitle.setText(Title);//显示正文标题
        textAuthor.setText(Auther);//显示作者
        textView.setText(Text);//显示文章内容
        textView.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX, 50);
        text = "";
        SharePreference sp = new SharePreference(MainActivity.this);
        int i=sp.getSize();//获取字号
        setsize(i);//设置字体大小
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

    @OnClick({R.id.toolbar, R.id.textView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                Toast.makeText(MainActivity.this, "toolbar", Toast.LENGTH_SHORT);
                break;
            case R.id.textView:
                //正文点击事件，调出底栏
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
                break;
        }
    }

    //设置字体背景颜色
    public void setTextColor(int t){
        if(t==0){
            textFinish.setBackgroundColor(this.getResources().getColor(R.color.whiteBack));
            textFinish.setTextColor(this.getResources().getColor(R.color.whiteFont));
            barTitle.setTextColor(this.getResources().getColor(R.color.whiteFont));
            textTitle.setBackgroundColor(this.getResources().getColor(R.color.whiteBack));
            textTitle.setTextColor(this.getResources().getColor(R.color.whiteFont));
            textAuthor.setBackgroundColor(this.getResources().getColor(R.color.whiteBack));
            textAuthor.setTextColor(this.getResources().getColor(R.color.whiteFont));
            textView.setBackgroundColor(this.getResources().getColor(R.color.whiteBack));
            textView.setTextColor(this.getResources().getColor(R.color.whiteFont));
            toolbar.setBackgroundColor(this.getResources().getColor(R.color.whiteBack));
            toolbar.setTitleTextColor(this.getResources().getColor(R.color.whiteFont));
        }
        else if(t==1){
            barTitle.setTextColor(this.getResources().getColor(R.color.greenFont));
            textFinish.setBackgroundColor(this.getResources().getColor(R.color.greenBack));
            textFinish.setTextColor(this.getResources().getColor(R.color.greenFont));
            textTitle.setBackgroundColor(this.getResources().getColor(R.color.greenBack));
            textTitle.setTextColor(this.getResources().getColor(R.color.greenFont));
            textAuthor.setBackgroundColor(this.getResources().getColor(R.color.greenBack));
            textAuthor.setTextColor(this.getResources().getColor(R.color.greenFont));
            textView.setBackgroundColor(this.getResources().getColor(R.color.greenBack));
            textView.setTextColor(this.getResources().getColor(R.color.greenFont));
            toolbar.setBackgroundColor(this.getResources().getColor(R.color.greenBack));
            toolbar.setTitleTextColor(this.getResources().getColor(R.color.greenFont));
        }
        else if(t==2){
            barTitle.setTextColor(this.getResources().getColor(R.color.yellowFont));
            textFinish.setBackgroundColor(this.getResources().getColor(R.color.yellowBack));
            textFinish.setTextColor(this.getResources().getColor(R.color.yellowFont));
            textTitle.setBackgroundColor(this.getResources().getColor(R.color.yellowBack));
            textTitle.setTextColor(this.getResources().getColor(R.color.yellowFont));
            textAuthor.setBackgroundColor(this.getResources().getColor(R.color.yellowBack));
            textAuthor.setTextColor(this.getResources().getColor(R.color.yellowFont));
            textView.setBackgroundColor(this.getResources().getColor(R.color.yellowBack));
            textView.setTextColor(this.getResources().getColor(R.color.yellowFont));
            toolbar.setBackgroundColor(this.getResources().getColor(R.color.yellowBack));
            toolbar.setTitleTextColor(this.getResources().getColor(R.color.yellowFont));
        }
        else if(t==3){
            barTitle.setTextColor(this.getResources().getColor(R.color.pinkFont));
            textFinish.setBackgroundColor(this.getResources().getColor(R.color.pinkBack));
            textFinish.setTextColor(this.getResources().getColor(R.color.pinkFont));
            textTitle.setBackgroundColor(this.getResources().getColor(R.color.pinkBack));
            textTitle.setTextColor(this.getResources().getColor(R.color.pinkFont));
            textAuthor.setBackgroundColor(this.getResources().getColor(R.color.pinkBack));
            textAuthor.setTextColor(this.getResources().getColor(R.color.pinkFont));
            textView.setBackgroundColor(this.getResources().getColor(R.color.pinkBack));
            textView.setTextColor(this.getResources().getColor(R.color.pinkFont));
            toolbar.setBackgroundColor(this.getResources().getColor(R.color.pinkBack));
            toolbar.setTitleTextColor(this.getResources().getColor(R.color.pinkFont));
        }
        else if(t==4){
            textFinish.setBackgroundColor(this.getResources().getColor(R.color.nightBack));
            textFinish.setTextColor(this.getResources().getColor(R.color.nightFont));
            barTitle.setTextColor(this.getResources().getColor(R.color.nightFont));
            textTitle.setBackgroundColor(this.getResources().getColor(R.color.nightBack));
            textTitle.setTextColor(this.getResources().getColor(R.color.nightFont));
            textAuthor.setBackgroundColor(this.getResources().getColor(R.color.nightBack));
            textAuthor.setTextColor(this.getResources().getColor(R.color.nightFont));
            textView.setBackgroundColor(this.getResources().getColor(R.color.nightBack));
            textView.setTextColor(this.getResources().getColor(R.color.nightFont));
            toolbar.setBackgroundColor(this.getResources().getColor(R.color.nightBack));
            toolbar.setTitleTextColor(this.getResources().getColor(R.color.nightFont));
        }
    }

}
