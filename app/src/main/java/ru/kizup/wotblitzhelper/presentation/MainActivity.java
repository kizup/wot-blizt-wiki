package ru.kizup.wotblitzhelper.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.presentation.main.view.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fl_container, new MainFragment());
            transaction.commit();
        }
    }
}
