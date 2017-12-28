package ru.kizup.wotblitzhelper.presentation.view.achievements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ItemViewHolder> {

    private List<AchievementUIModel> mItems;
    private Context mContext;
    private OnItemClickListener<AchievementUIModel> mItemClickListener;

    public AchievementsAdapter(Context context,
                               @NonNull OnItemClickListener<AchievementUIModel> listener) {
        mContext = context;
        mItems = new ArrayList<>();
        mItemClickListener = listener;
    }

    public void setItems(List<AchievementUIModel> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_achievement, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        AchievementUIModel achievementUIModel = mItems.get(position);
        holder.mAchievementNameTextView.setText(achievementUIModel.getName());
        holder.itemView.setOnClickListener(v -> mItemClickListener.onItemClick(achievementUIModel));
        Picasso.with(mContext)
                .load(achievementUIModel.getImage())
                .into(holder.mAchievementImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_achievement_name)
        TextView mAchievementNameTextView;
        @BindView(R.id.iv_achievement)
        ImageView mAchievementImageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
