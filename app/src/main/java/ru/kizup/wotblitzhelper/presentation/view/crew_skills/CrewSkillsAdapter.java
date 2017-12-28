package ru.kizup.wotblitzhelper.presentation.view.crew_skills;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsAdapter extends SectionedRecyclerViewAdapter<SectionedViewHolder> {

    private OnItemClickListener<CrewSkillUIModel> mItemClickListener;
    private Context mContext;
    private HashMap<CrewSkillSectionUIModel, List<CrewSkillUIModel>> mItemsMap;
    private List<CrewSkillSectionUIModel> mHeaders;

    public CrewSkillsAdapter(Context context, @NonNull OnItemClickListener<CrewSkillUIModel> itemClickListener) {
        mItemClickListener = itemClickListener;
        mContext = context;
        mItemsMap = new HashMap<>();
        mHeaders = new ArrayList<>();
        shouldShowFooters(false);
    }

    public void setItems(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> items) {
        mItemsMap.clear();
        mItemsMap.putAll(items);
        mHeaders.clear();
        mHeaders.addAll(items.keySet());
        notifyDataSetChanged();
    }

    @Override
    public SectionedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                View header = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_crew_skill_section, parent, false);
                return new HeaderViewHolder(header, this);
            }
        }
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_crew_skill, parent, false);
        return new ItemViewHolder(item);
    }

    @Override
    public int getSectionCount() {
        return mItemsMap.keySet().size();
    }

    @Override
    public int getItemCount(int section) {
        return mItemsMap.get(mHeaders.get(section)).size();
    }

    @Override
    public void onBindHeaderViewHolder(SectionedViewHolder holder, int section, boolean expanded) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.mHeaderTextView.setText(mHeaders.get(section).getName());
        headerViewHolder.mCaretImageView.setImageResource(expanded ? R.drawable.ic_collapse : R.drawable.ic_expand);
    }

    @Override
    public void onBindFooterViewHolder(SectionedViewHolder holder, int section) {
    }

    @Override
    public void onBindViewHolder(SectionedViewHolder holder, int section, int relativePosition, int absolutePosition) {
        CrewSkillUIModel item = mItemsMap.get(mHeaders.get(section)).get(relativePosition);
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.mCrewSkillNameTextView.setText(item.getName());
        viewHolder.itemView.setOnClickListener(v -> mItemClickListener.onItemClick(item));
        Picasso.with(mContext)
                .load(item.getImageUrl())
                .into(viewHolder.mCrewSkillImageView);
        viewHolder.startAnimation();
    }

    static class HeaderViewHolder extends SectionedViewHolder
            implements View.OnClickListener {

        @BindView(R.id.iv_caret)
        ImageView mCaretImageView;
        @BindView(R.id.tv_crew_skill_section_name)
        TextView mHeaderTextView;

        private SectionedRecyclerViewAdapter mAdapter;

        HeaderViewHolder(View itemView, CrewSkillsAdapter adapter) {
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

        @BindView(R.id.tv_crew_skill_name)
        TextView mCrewSkillNameTextView;
        @BindView(R.id.iv_crew_skill)
        ImageView mCrewSkillImageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void startAnimation() {
            Animation animation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.item_animation_fall_down);
            animation.setDuration(itemView.getResources().getInteger(R.integer.anim_duration_medium));
            itemView.startAnimation(animation);
        }
    }

}
