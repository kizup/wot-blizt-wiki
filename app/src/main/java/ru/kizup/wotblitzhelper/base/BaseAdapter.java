//package ru.kizup.wotblitzhelper.base;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by: dpuzikov on 19.12.17.
// * e-mail: kizup.diman@gmail.com
// * Skype: kizupx
// */
//
//public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>> {
//
//    private List<T> mItems;
//    private Context mContext;
//    protected OnItemClickListener<T> mOnItemClickListener;
//
//    public BaseAdapter(Context context, OnItemClickListener<T> listener) {
//        mContext = context;
//        mOnItemClickListener = listener;
//        mItems = new ArrayList<>();
//    }
//
//    public void setItems(List<T> items) {
//        mItems.clear();
//        mItems.addAll(items);
//        notifyDataSetChanged();
//    }
//
//    public void addItems(T item) {
//        mItems.add(item);
//        notifyItemInserted(mItems.size() - 1);
//    }
//
//    protected T getItem(int position) {
//        return mItems.get(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItems.size();
//    }
//
//    public static abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
//
//        public BaseViewHolder(View itemView) {
//            super(itemView);
//        }
//
//        public abstract void bind(T item, OnItemClickListener<T> listener);
//    }
//
//}
