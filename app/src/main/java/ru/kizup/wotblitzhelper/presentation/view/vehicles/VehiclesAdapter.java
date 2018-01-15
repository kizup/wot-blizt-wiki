package ru.kizup.wotblitzhelper.presentation.view.vehicles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.utils.RomanNumber;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 12.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.VehicleViewHolder> {

    private Context mContext;
    private OnItemClickListener<ShortVehicleInfoUIModel> mItemClickListener;
    private List<ShortVehicleInfoUIModel> mItems;
    private Picasso mPicasso;

    VehiclesAdapter(@NonNull Context context,
                           @NonNull OnItemClickListener<ShortVehicleInfoUIModel> itemClickListener,
                           @NonNull Picasso picasso) {
        mContext = context;
        mItemClickListener = itemClickListener;
        mPicasso = picasso;
        mItems = new ArrayList<>();
    }

    public void setItems(List<ShortVehicleInfoUIModel> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_short_info, parent, false);
        return new VehicleViewHolder(root);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        ShortVehicleInfoUIModel model = mItems.get(position);
        holder.mVehicleNameTextView.setText(model.getName());
        holder.mVehicleTierTextView.setText(RomanNumber.toRoman(model.getTier()));

        mPicasso.load(model.getSmallImageUrl())
                .into(holder.mVehicleImageView);
        holder.itemView.setBackground(ContextCompat.getDrawable(mContext, model.isPremium()
                ? R.drawable.vehicle_premium_bg
                : R.drawable.vehicle_bg));
        holder.itemView.setOnClickListener(v -> mItemClickListener.onItemClick(model));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_vehicle)
        ImageView mVehicleImageView;
        @BindView(R.id.tv_vehicle_name)
        TextView mVehicleNameTextView;
        @BindView(R.id.tv_vehicle_tier)
        TextView mVehicleTierTextView;

        VehicleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
