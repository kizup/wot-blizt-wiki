package ru.kizup.wotblitzhelper.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseActivity;
import ru.kizup.wotblitzhelper.presentation.view.main.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_container, new MainFragment());
            transaction.commit();
        }
    }
}
