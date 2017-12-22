package xzr.fuck_school_electronic_board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by xzr on 2017/11/11.
 */

public class Shell {
    public static String su(String cmd)throws Exception{
        String result="";
        Process p=new ProcessBuilder("su").redirectErrorStream(true).start();
        OutputStreamWriter o=new OutputStreamWriter(p.getOutputStream());
        BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
        o.write(cmd+"\n"+"exit\n");
        o.flush();
        String s;
        while((s=b.readLine())!=null){
            result=result+s+"\n";
        }
        p.destroy();
        o.close();
        b.close();

            result=result.substring(0,result.length()-1);


        return result;
    }
}
