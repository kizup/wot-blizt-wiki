package ru.kizup.wotblitzhelper.presentation.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;
import ru.kizup.wotblitzhelper.utils.RomanNumber;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 10.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ModuleButton extends RelativeLayout
        implements View.OnClickListener {

    @BindView(R.id.iv_module)
    ImageView mModuleImage;
    @BindView(R.id.tv_module_tier)
    TextView mModuleTierTextView;

    private Unbinder mUnbinder;
    private Picasso mPicasso;
    private VehicleModule mVehicleModule;
    private OnItemClickListener<VehicleModule> mItemClickListener;

    public ModuleButton(Context context) {
        super(context);
        init(context);
    }

    public ModuleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ModuleButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.module_button, this);
        mUnbinder = ButterKnife.bind(this, root);
        mPicasso = Picasso.with(context);

        setOnClickListener(this);
    }

    public void setOnItemClickListener(OnItemClickListener<VehicleModule> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setVehicleModule(VehicleModule vehicleModule) {
        mVehicleModule = vehicleModule;
        setModuleTier(vehicleModule.getTier());
        loadModuleImageUrl(vehicleModule.getType().getImageUrl());
    }

    private void loadModuleImageUrl(String imageUrl) {
        mPicasso.load(imageUrl)
                .into(mModuleImage);
    }

    private void setModuleTier(int tier) {
        mModuleTierTextView.setText(RomanNumber.toRoman(tier));
    }

    @Override
    protected void onDetachedFromWindow() {
        mUnbinder.unbind();
        super.onDetachedFromWindow();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null && mVehicleModule != null)
            mItemClickListener.onItemClick(mVehicleModule);
    }
}
