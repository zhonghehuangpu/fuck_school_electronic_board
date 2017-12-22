package xzr.fuck_school_electronic_board;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil;

import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.se;
import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.sh;


public class MainActivity extends Activity {
    Switch sw1;
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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();

        try {
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

}
