package xzr.fuck_school_electronic_board;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
