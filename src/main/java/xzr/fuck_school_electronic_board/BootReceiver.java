package xzr.fuck_school_electronic_board;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Intent i=new Intent(context,Fuck_service.class);
        context.startService(i);


    }
}
