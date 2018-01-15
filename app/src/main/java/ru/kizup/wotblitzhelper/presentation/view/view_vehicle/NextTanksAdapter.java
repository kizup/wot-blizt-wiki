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
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.utils.RomanNumber;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class NextTanksAdapter extends RecyclerView.Adapter<NextTanksAdapter.ItemViewHolder> {

    private List<ShortVehicleInfoUIModel> mItems;
    private Context mContext;
    private Picasso mPicasso;
    private OnItemClickListener<ShortVehicleInfoUIModel> mItemClickListener;

    public NextTanksAdapter(Context context,
                            Picasso picasso,
                            OnItemClickListener<ShortVehicleInfoUIModel> listener) {
        mContext = context;
        mPicasso = picasso;
        mItemClickListener = listener;
        mItems = new ArrayList<>();
    }

    public void setItems(List<ShortVehicleInfoUIModel> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_short_info, parent, false);
        return new ItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
        ShortVehicleInfoUIModel model = mItems.get(position);
        viewHolder.mVehicleNameTextView.setText(model.getName());
        viewHolder.mVehicleTierTextView.setText(RomanNumber.toRoman(model.getTier()));
        viewHolder.itemView.setOnClickListener(v -> {
            if (mItemClickListener != null) mItemClickListener.onItemClick(model);
        });
        mPicasso
                .load(model.getSmallImageUrl())
                .into(viewHolder.mVehicleImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_vehicle)
        ImageView mVehicleImageView;
        @BindView(R.id.tv_vehicle_tier)
        TextView mVehicleTierTextView;
        @BindView(R.id.tv_vehicle_name)
        TextView mVehicleNameTextView;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
