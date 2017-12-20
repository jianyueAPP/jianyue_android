//存用户数据

package test.com.jianyue.reader_activity.Read_activity;

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
    //设置背景颜色的单选框为真
    public void setWhiteTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("WhiteChecked", true);
        editor.commit();
    }
    public void setGreenTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("GreenChecked", true);
        editor.commit();
    }
    public void setYellowTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("YellowChecked", true);
        editor.commit();
    }
    public void setPinkTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("PinkChecked", true);
        editor.commit();
    }
    //设置夜间模式开关为真
    public void setNightTrue(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("NightChecked", true);
        editor.commit();
    }
    //设置字号
    public void setSize(int i){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Size",i);
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
    //设置背景颜色的单选框为假
    public void setWhiteFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("WhiteChecked", false);
        editor.commit();
    }
    public void setGreenFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("GreenChecked", false);
        editor.commit();
    }
    public void setYellowFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("YellowChecked", false);
        editor.commit();
    }
    public void setPinkFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("PinkChecked", false);
        editor.commit();
    }
    //设置夜间模式开关为真
    public void setNightFalse(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("NightChecked", false);
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
    //获取背景颜色
    public boolean getWhite()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("WhiteChecked", false);
        return b;
    }
    public boolean getGreen()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("GreenChecked", false);
        return b;
    }
    public boolean getYellow()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("YellowChecked", false);
        return b;
    }
    public boolean getPink()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("PinkChecked", false);
        return b;
    }
    //获取夜间模式开关状态
    public boolean getNight()
    {
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("NightChecked", false);
        return b;
    }
    //获取字号大小
    public int getSize(){
        SharedPreferences sp = context.getSharedPreferences("tags_save", Context.MODE_PRIVATE);
        int b=sp.getInt("Size",1);
        return b;
    }

}
