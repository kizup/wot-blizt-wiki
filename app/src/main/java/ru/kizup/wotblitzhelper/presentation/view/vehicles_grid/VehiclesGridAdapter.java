package ru.kizup.wotblitzhelper.presentation.view.vehicles_grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_EMPTY = 1;
    private final int VIEW_TYPE_HEADER = 2;

    private Context mContext;
    private OnItemClickListener<ShortVehicleInfoUIModel> mItemClickListener;
    private List<ShortVehicleInfoUIModel> mItems;
    private Picasso mPicasso;

    VehiclesGridAdapter(Context context,
                        OnItemClickListener<ShortVehicleInfoUIModel> listener,
                        Picasso picasso) {
        mContext = context;
        mItems = new ArrayList<>();
        mPicasso = picasso;
        mItemClickListener = listener;
    }

    public void setItems(List<ShortVehicleInfoUIModel> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_short_info_grid, parent, false);

        switch (viewType) {
            case VIEW_TYPE_EMPTY: {
                return new DummyViewHolder(item);
            }
            case VIEW_TYPE_HEADER: {
                item = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_vehicle_short_info_grid_header,
                                parent,
                                false);
                return new HeaderViewHolder(item);
            }
            default: {
                return new VehicleViewHolder(item);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (mItems.get(position).getModelType()) {
            case EMPTY: {
                return VIEW_TYPE_EMPTY;
            }
            case HEADER: {
                return VIEW_TYPE_HEADER;
            }
            case VEHICLE: {
                return VIEW_TYPE_ITEM;
            }
        }
        return VIEW_TYPE_EMPTY;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShortVehicleInfoUIModel model = mItems.get(position);

        switch (model.getModelType()) {
            case VEHICLE: {
                VehicleViewHolder viewHolder = (VehicleViewHolder) holder;
                viewHolder.itemView.setOnClickListener(v -> {
                    if (mItemClickListener != null) mItemClickListener.onItemClick(model);
                });
                mPicasso
                        .load(model.getSmallImageUrl())
                        .into(viewHolder.mVehicleImageView);
                break;
            }
            case HEADER: {
                HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
                mPicasso.load(model.getTypeDrawable())
                        .into(viewHolder.mHeaderImageView);
                break;
            }
            case EMPTY: {
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_vehicle_header)
        ImageView mHeaderImageView;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class DummyViewHolder extends RecyclerView.ViewHolder {

        DummyViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_vehicle)
        ImageView mVehicleImageView;

        VehicleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
