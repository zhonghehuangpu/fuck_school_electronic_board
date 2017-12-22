package xzr.fuck_school_electronic_board;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.text.SimpleDateFormat;

import xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil;

public class Fuck_service extends Service {
    boolean running;
    PendingIntent p(){
        Intent i=new Intent(this,MainActivity.class);
        return PendingIntent.getActivity(Fuck_service.this,0,i.setAction("fuck_now"),PendingIntent.FLAG_CANCEL_CURRENT);
    }
SimpleDateFormat df(){
    return new SimpleDateFormat("HH:mm:ss");
}
    class fuck_thread extends Thread{
        public void run(){
            while (true){
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
                    Thread.sleep(1000);

                }
                catch (Exception e){

                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    class fuck_now extends Thread{
    public void run(){
        try {
            new Shell().su("am force-stop com.surfkj.maincard");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    try {
        if(intent.getAction().equals("fuck_now")){
            new fuck_now().start();

        }
    }
    catch (Exception e){

    }
    if(!running){
        new fuck_thread().start();
        running=true;
    }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
    startService(new Intent(this,Fuck_service.class));
    }
}
