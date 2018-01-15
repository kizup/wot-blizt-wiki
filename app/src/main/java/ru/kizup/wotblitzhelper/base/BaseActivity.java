package ru.kizup.wotblitzhelper.base;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by: dpuzikov on 19.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class BaseActivity extends AppCompatActivity
        implements BaseFragment.OnBaseFragmentInteractionListener {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackArrowClicked() {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            finishAfterTransition();
//        } else {
//            finish();
//        }
        super.onBackPressed();
    }
}
