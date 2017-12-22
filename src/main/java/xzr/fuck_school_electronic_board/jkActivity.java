package xzr.fuck_school_electronic_board;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import static xzr.fuck_school_electronic_board.MainActivity.toast;
import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.se;
import static xzr.fuck_school_electronic_board.utils.SharedPreferenceUtil.sh;

public class jkActivity extends Activity {
EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        e1=(EditText)findViewById(R.id.ide);
        e1.setText(sh.getString("cid",""));
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
    public void save_jk(View v){
        se.putString("cid",e1.getText().toString());
        se.commit();
        toast("保存成功",this);
    }
}
