package xzr.fuck_school_electronic_board;

/**
 * Created by xzr on 2017/12/22.
 */

public class cmds {
    public static class fuck_now extends Thread{
        public void run(){
            try {
               Shell.su("am force-stop com.surfkj.maincard");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static class jk extends Thread{
        String id;
        public jk(String id){
            this.id=id;
            this.run();
        }
        public void run(){
            try {
                Shell.su("input text \""+id+"\n\"");
            }
            catch (Exception e){

            }
        }
    }
}
