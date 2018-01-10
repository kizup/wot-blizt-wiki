package ru.kizup.wotblitzhelper.presentation.view.view_vehicle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.models.view_vehicle.EngineDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.SuspensionDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.TurretDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;

/**
 * Created by: dpuzikov on 10.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehicleModulesAdapter extends RecyclerView.Adapter<VehicleModulesAdapter.VehicleModuleViewHolder> {

    private List<VehicleModule> mItems;
    private Context mContext;
    private Picasso mPicasso;

    public VehicleModulesAdapter(Context context, Picasso picasso) {
        mContext = context;
        mPicasso = picasso;
        mItems = new ArrayList<>();
    }

    public void setItems(List<? extends VehicleModule> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public VehicleModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (mItems.get(viewType).getType()) {
            case TURRET: {
                View root = LayoutInflater.from(mContext).inflate(R.layout.item_module_turret, parent, false);
                return new TurretViewHolder(root);
            }
            case GUN: {
                View root = LayoutInflater.from(mContext).inflate(R.layout.item_module_gun, parent, false);
                return new GunViewHolder(root);
            }
            case ENGINE: {
                View root = LayoutInflater.from(mContext).inflate(R.layout.item_module_engine, parent, false);
                return new EngineViewHolder(root);
            }
            case SUSPENSION: {
                View root = LayoutInflater.from(mContext).inflate(R.layout.item_module_suspension, parent, false);
                return new SuspensionViewHolder(root);
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(VehicleModuleViewHolder holder, int position) {
        VehicleModule module = mItems.get(position);
        mPicasso.load(module.getType().getImageUrl())
                .into(holder.mModuleImageView);
        holder.mModuleNameTextView.setText(module.getName());

        holder.bind(module);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static abstract class VehicleModuleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_module)
        ImageView mModuleImageView;
        @BindView(R.id.tv_module_name)
        TextView mModuleNameTextView;

        VehicleModuleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        abstract void bind(VehicleModule module);
    }

    static class TurretViewHolder extends VehicleModuleViewHolder {

        TurretViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(VehicleModule module) {
            TurretDataModel model = (TurretDataModel) module;
        }
    }

    static class GunViewHolder extends VehicleModuleViewHolder {

        GunViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(VehicleModule module) {
            GunDataModel model = (GunDataModel) module;
        }
    }

    static class SuspensionViewHolder extends VehicleModuleViewHolder {

        SuspensionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(VehicleModule module) {
            SuspensionDataModel model = (SuspensionDataModel) module;
        }
    }

    static class EngineViewHolder extends VehicleModuleViewHolder {

        EngineViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(VehicleModule module) {
            EngineDataModel model = (EngineDataModel) module;
        }
    }

}
