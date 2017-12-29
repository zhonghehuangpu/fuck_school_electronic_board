package xzr.fuck_school_electronic_board;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.text.SimpleDateFormat;

import xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil;

import static xzr.fuck_school_electronic_board.MainActivity.toast;
import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.sh;

public class Fuck_service extends Service {
    PendingIntent p(){
        Intent i=new Intent(this,MainActivity.class);
        return PendingIntent.getActivity(Fuck_service.this,0,i.setAction("fuck_now"),PendingIntent.FLAG_CANCEL_CURRENT);
    }

    PendingIntent p1(){
        Intent i=new Intent(this,MainActivity.class);
        return PendingIntent.getActivity(Fuck_service.this,0,i.setAction("jk_now"),PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferenceUtil.init(this);
    try {
        if(intent.getAction().equals("fuck_now")){
            new cmds.fuck_now().start();
        }
        if(intent.getAction().equals("jk")){
            String cid=sh.getString("cid","");
            if(!cid.equals("")) {
                new cmds.jk(cid);
            }
            else{
                toast("尚未初始化",this);
            }

        }
    }
    catch (Exception e){

    }
        try{
            NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            if(SharedPreferenceUtil.gettz()) {
                Notification baseNF = new Notification.Builder(Fuck_service.this)//设置启动的context
                        .setContentTitle("干翻电子班牌")//设置标题
                        .setContentText("轻触此处来干翻电子班牌")//设置内容
                        .setSmallIcon(R.mipmap.ic_launcher)//设置要显示的两个图片 小图片可以设置资源文件，大图片为bitmap类型所以需要decodeResource
                        // .setChannelId(NotificationChannel.DEFAULT_CHANNEL_ID)
                        //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                        .setOngoing(true)
                        .setContentIntent(p())
                        .build();
                nm.notify(1, baseNF);
            }
            else{
                nm.cancel(1);
            }


            //刷假卡

            if(SharedPreferenceUtil.getjk()) {
                Notification baseNF = new Notification.Builder(Fuck_service.this)//设置启动的context
                        .setContentTitle("刷假卡")//设置标题
                        .setContentText("轻触此处来模拟一次刷卡")//设置内容
                        .setSmallIcon(R.mipmap.ic_launcher)//设置要显示的两个图片 小图片可以设置资源文件，大图片为bitmap类型所以需要decodeResource
                        // .setChannelId(NotificationChannel.DEFAULT_CHANNEL_ID)
                        //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                        .setOngoing(true)
                        .setContentIntent(p1())
                        .build();
                nm.notify(2, baseNF);
            }
            else{
                nm.cancel(2);
            }

        }
        catch (Exception e){

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
    startService(new Intent(this,Fuck_service.class));
    }
}
