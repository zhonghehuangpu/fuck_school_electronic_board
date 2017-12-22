package xzr.fuck_school_electronic_board.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xzr on 2017/12/22.
 */

public class SharedPreferenceUtil {
    public static SharedPreferences sh;
    public static SharedPreferences.Editor se;
    public static void init(Context context){
        sh=context.getSharedPreferences("main",0);
        se=sh.edit();
    }
    public static boolean gettz(){
        return sh.getBoolean("tz",false);
    }
}
