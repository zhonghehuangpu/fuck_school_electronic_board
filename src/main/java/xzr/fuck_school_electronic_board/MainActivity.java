package xzr.fuck_school_electronic_board;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil;
import xzr.fuck_school_electronic_board.utils.versionutil;

import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.se;
import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.sh;


public class MainActivity extends Activity {
    Switch sw1;
    Switch sw2;
    TextView t1;
    AlertDialog dia;
    class start_settings extends Thread{
        public void run(){
            try {
                Thread.sleep(500);
                Intent intent = new Intent();
                ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.Settings");
                intent.setComponent(cn);
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    void initviews(){
        SharedPreferenceUtil.init(this);
        final View pwdv=LayoutInflater.from(this).inflate(R.layout.pwd_layout,null);
        dia=new AlertDialog.Builder(this)
                .setTitle("输入“干翻电子班牌”的超级管理员密码")
                .setView(pwdv)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText eee=(EditText)pwdv.findViewById(R.id.pwde);
                        if(eee.getText().toString().equals("pwd")){
                            startActivity(new Intent(MainActivity.this,jkActivity.class));
                            eee.setText("");
                        }
                        else {
                            toast("密码错误！",MainActivity.this);
                        }
                    }
                })
                .setNegativeButton("取消",null)
                .create();
        sw1=(Switch)findViewById(R.id.sw1);
        if(SharedPreferenceUtil.gettz()){
            sw1.setChecked(true);
        }
        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw1.isChecked()){
                    se.putBoolean("tz",true);

                }
                else{
                    se.putBoolean("tz",false);
                }
                se.commit();
                startService(new Intent(MainActivity.this,Fuck_service.class));
            }
        });


        sw2=(Switch)findViewById(R.id.sw2);
        if(SharedPreferenceUtil.getjk()){
            sw2.setChecked(true);
        }
        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw2.isChecked()){
                    se.putBoolean("jk",true);

                }
                else{
                    se.putBoolean("jk",false);
                }
                se.commit();
                startService(new Intent(MainActivity.this,Fuck_service.class));
            }
        });
        t1=(TextView)findViewById(R.id.t1);
        t1.setText("v"+versionutil.getLocalVersionName(this));
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.show();
            }
        });
    }
    class jk extends Thread{
        public void run(){
            try {
                Thread.sleep(500);
                startService(new Intent(MainActivity.this,Fuck_service.class).setAction("jk"));
            }

            catch (Exception e){

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();

        try {
            if(getIntent().getAction().equals("jk_now")){
                finish();
                new jk().start();
                return;
            }
            if (getIntent().getAction().equals("fuck_now")||getIntent().getAction().equals("android.intent.action.SEND")) {
                startService(new Intent(this, Fuck_service.class).setAction("fuck_now"));
                finish();
                new start_settings().start();
            }
            else{
                startService(new Intent(this, Fuck_service.class));
            }
        }
        catch (Exception e){
            startService(new Intent(this, Fuck_service.class));
        }
    }
    public static void toast(String text, Context context){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }

}
