package ru.kizup.wotblitzhelper.presentation.view.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;
import ru.kizup.wotblitzhelper.utils.RomanNumber;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesAdapter extends SectionedRecyclerViewAdapter<SectionedViewHolder> {

    private Context mContext;
    private Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>> mItems;
    private List<ShortVehicleSection> mHeaders;
    private OnItemClickListener<ShortVehicleInfoUIModel> mItemClickListener;

    public VehiclesAdapter(Context context,
                           OnItemClickListener<ShortVehicleInfoUIModel> listener) {
        mContext = context;
        mItems = new TreeMap<>();
        mHeaders = new ArrayList<>();
        mItemClickListener = listener;
    }

    public void setItems(Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>> items) {
        mHeaders.clear();
        mHeaders.addAll(items.keySet());
        mItems.clear();
        mItems.putAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getSectionCount() {
        return mHeaders.size();
    }

    @Override
    public int getItemCount(int section) {
        return mItems.get(mHeaders.get(section)).size();
    }

    @Override
    public void onBindHeaderViewHolder(SectionedViewHolder holder, int section, boolean expanded) {
        HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
        ShortVehicleSection vehicleSection = mHeaders.get(section);
        viewHolder.mCaretImageView.setImageResource(expanded ? R.drawable.ic_collapse : R.drawable.ic_expand);
        switch (vehicleSection.getFilterType()) {
            case BY_TYPE: {
                viewHolder.mHeaderTextView.setText(String.valueOf(vehicleSection.getType().getName()));
                break;
            }
            case BY_NATION: {
                viewHolder.mHeaderTextView.setText(String.valueOf(vehicleSection.getNation().getName()));
                break;
            }
            default: {
                viewHolder.mHeaderTextView.setText(String.valueOf(RomanNumber.toRoman(vehicleSection.getTier())));
                break;
            }
        }
    }

    @Override
    public void onBindFooterViewHolder(SectionedViewHolder holder, int section) {

    }

    @Override
    public void onBindViewHolder(SectionedViewHolder holder,
                                 int section,
                                 int relativePosition,
                                 int absolutePosition) {
        ShortVehicleInfoUIModel model = mItems.get(mHeaders.get(section)).get(relativePosition);
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.mVehicleNameTextView.setText(model.getName());
        viewHolder.mVehicleTierTextView.setText(RomanNumber.toRoman(Integer.parseInt(model.getTier())));
        viewHolder.itemView.setOnClickListener(v -> {
            if (mItemClickListener != null) mItemClickListener.onItemClick(model);
        });
        Picasso.with(mContext)
                .load(model.getSmallImageUrl())
                .into(viewHolder.mVehicleImageView);
    }

    @Override
    public SectionedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View header = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_section, parent, false);
                return new HeaderViewHolder(header, this);
            }
        }
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_vehicle_short_info, parent, false);
        return new ItemViewHolder(item);
    }

    static class HeaderViewHolder extends SectionedViewHolder
            implements View.OnClickListener {

        @BindView(R.id.iv_caret)
        ImageView mCaretImageView;
        @BindView(R.id.tv_vehicle_section_name)
        TextView mHeaderTextView;

        private SectionedRecyclerViewAdapter mAdapter;

        HeaderViewHolder(View itemView, VehiclesAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mAdapter.toggleSectionExpanded(getRelativePosition().section());
        }
    }

    static class ItemViewHolder extends SectionedViewHolder {

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
