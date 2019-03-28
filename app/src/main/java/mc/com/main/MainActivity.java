package mc.com.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import mc.com.tools.AnimUtil;
import mc.com.tools.Util;

public class MainActivity extends BaseActivity{
    private int duration;
    private Button start=null;

    /**
     * TODO : activities transition..
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        loadingAnimation();

        //customActionBar();
        /*setMenu(
                R.menu.main_menu,
                id->{
                    if(id==R.id.mi_about)
                        Util.show(this, NavigationActivity.class);
                }
        );*/
    }


    @Override
    protected void onPause() {
        super.onPause();
        start.animate().alpha(1f).setDuration(duration);
        start.setOnClickListener(v->Util.show(getApplicationContext(), NavigationActivity.class));
    }

    private void loadingAnimation() {
        ProgressBar loading= findViewById(R.id.loading);
        duration = getResources().getInteger(android.R.integer.config_longAnimTime);

        start = findViewById(R.id.btn_start);
        start.setAlpha(0f);
        start.setVisibility(View.VISIBLE);

        loading.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                       loading.setVisibility(View.GONE);
                       display();
                    }

                    private void display() {

                        AnimUtil.CircularReveal(findViewById(R.id.tvTitle));
                        AnimUtil.CircularReveal(findViewById(R.id.logo));
                        
                        gotoAfterDelay();

                        //Util.hideSystemUI(getWindow());
                        //Util.customToolbar(getSupportActionBar(), R.drawable.duke);
                        //getSupportActionBar().show();
                    }

                    private void gotoAfterDelay() {
                        new Handler().postDelayed(
                                ()->Util.show(getApplicationContext(), NavigationActivity.class)
                                , duration
                        );
                    }
                });
    }
}
