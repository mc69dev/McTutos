package mc.com.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import mc.com.tools.Util;

public class BaseActivity extends AppCompatActivity {
    protected static final String TAG ="tests" ;

    private Integer menu_res;
    //functional interface
    interface Listener{ void action(int id); }
    private Listener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    protected void setMenu(int menu_res, Listener listener){
        this.menu_res=menu_res;
        this.listener=listener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(menu_res!=null)
            getMenuInflater().inflate(menu_res,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(menu_res!=null)
            listener.action(item.getItemId());
        return super.onOptionsItemSelected(item);
    }


    protected void customActionBar() {
        int main_color = getResources().getColor(android.R.color.holo_blue_light, null);
        findViewById(R.id.main_container).setBackgroundColor(main_color);
        Util.customToolbar(getSupportActionBar(), R.drawable.duke, main_color);
    }
}
