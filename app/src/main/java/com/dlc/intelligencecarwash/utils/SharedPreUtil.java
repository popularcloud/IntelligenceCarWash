package com.dlc.intelligencecarwash.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dlc.intelligencecarwash.mvp.model.entity.UserInfoEntity;

import java.io.IOException;
import java.io.StreamCorruptedException;

public class SharedPreUtil
{
     
    // 用户名key
    public final static String KEY_NAME = "KEY_NAME";
 
/*    public final static String KEY_HEADER = "KEY_HEADER";

    public final static String KEY_PHONE = "KEY_PHONE";*/

     
    private static SharedPreUtil s_SharedPreUtil;
     
    private static UserInfoEntity s_User = null;
 
    private SharedPreferences msp;
     
    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context)
    {
        if (s_SharedPreUtil == null)
        {
            s_SharedPreUtil = new SharedPreUtil(context);
        }
    }
     
    /**
     * 获取唯一的instance
     *
     * @return
     */
    public static synchronized SharedPreUtil getInstance()
    {
        return s_SharedPreUtil;
    }
     
    public SharedPreUtil(Context context)
    {
        msp = context.getSharedPreferences("user_info",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }
     
    public SharedPreferences getSharedPref()
    {
        return msp;
    }
 
     
    public synchronized void putUser(UserInfoEntity user)
    {
         
        SharedPreferences.Editor editor = msp.edit();
         
        String str="";
        try {
            str = SerializableUtil.obj2Str(user);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        editor.putString(KEY_NAME,str);
        editor.commit();
         
        s_User = user;
    }
     
    public synchronized UserInfoEntity getUser()
    {
        if (s_User == null)
        {
            s_User = new UserInfoEntity();
            //获取序列化的数据
            String str = msp.getString(SharedPreUtil.KEY_NAME, "");
             
            try {
                Object obj = SerializableUtil.str2Obj(str);
                if(obj != null){
                    s_User = (UserInfoEntity)obj;
                }
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
         
        return s_User;
    }
     
    public synchronized void DeleteUser()
    {
        SharedPreferences.Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");
        editor.commit();
        s_User = null;
    }
     
}