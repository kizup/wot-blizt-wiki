package ru.kizup.wotblitzhelper.presentation.achievements.view;

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
import ru.kizup.wotblitzhelper.presentation.achievements.models.Achievement;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ItemViewHolder> {

    private List<Achievement> mItems;
    private Context mContext;

    public AchievementsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
    }

    public void setItems(List<Achievement> items) {
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
        Achievement achievement = mItems.get(position);
        holder.mAchievementNameTextView.setText(achievement.getName());
        Picasso.with(mContext)
                .load(achievement.getImage())
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
