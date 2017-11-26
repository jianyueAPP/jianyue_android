//存用户数据

package test.com.jianyue;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 86758 on 2017/11/14 0014.
 */


public class SharePreference {

    public Context context;
    public SharePreference(Context context)
    {
        this.context = context;
    }

    /****设置状态  false为安装后第一次登录，true为已经登录过****/
    public void setState()
    {
        SharedPreferences sp = context.getSharedPreferences("first_login_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", true);
        editor.commit();
    }

    //设置复选框标签为true，即选中
    public void setMeiWenTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("MeiWenChecked", true);
        editor.commit();
    }
    public void setQinGanTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("QinGanChecked", true);
        editor.commit();
    }
    public void setZhenTanTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("ZhenTanChecked", true);
        editor.commit();
    }
    public void setLiShiTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LiShiChecked", true);
        editor.commit();
    }
    public void setLiZhiTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LiZhiChecked", true);
        editor.commit();
    }
    public void setYouMoTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("YouMoChecked", true);
        editor.commit();
    }


    //设置复选框标签为false，即未选中
    public void setMeiWenFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("MeiWenChecked", false);
        editor.commit();
    }
    public void setQinGanFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("QinGanChecked", false);
        editor.commit();
    }
    public void setZhenTanFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("ZhenTanChecked", false);
        editor.commit();
    }
    public void setLiShiFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LiShiChecked", false);
        editor.commit();
    }
    public void setLiZhiFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LiZhiChecked", false);
        editor.commit();
    }
    public void setYouMoFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("YouMoChecked", false);
        editor.commit();
    }


    /***获取状态***/
    public boolean getState()
    {
        SharedPreferences sp = context.getSharedPreferences("first_login_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("isLogin", false);
        return b;
    }

    public boolean getMeiWen()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("MeiWenChecked", false);
        return b;
    }
    public boolean getQinGan()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("QinGanChecked", false);
        return b;
    }
    public boolean getZhenTan()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("ZhenTanChecked", false);
        return b;
    }
    public boolean getLiShi()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("LiShiChecked", false);
        return b;
    }
    public boolean getLiZhi()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("LiZhiChecked", false);
        return b;
    }
    public boolean getYouMo()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("YouMoChecked", false);
        return b;
    }

}
