package com.crisspian.recyclerviewexamples_01.adapter;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crisspian.recyclerviewexamples_01.databinding.ItemListDataBinding;
import com.crisspian.recyclerviewexamples_01.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private List<Item> itemList;
    private PassData callBack;

    public ItemAdapter(List<Item> itemList, PassData mCallBack) {
        this.itemList = itemList;
        this.callBack = mCallBack;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListDataBinding mBinding = ItemListDataBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ItemViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item mItem = itemList.get(position);
        holder.textView.setText(mItem.getItemDescription());
        Glide.with(holder.itemView.getContext()).load(mItem.getUrlImage())
                .centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;

        public ItemViewHolder(@NonNull ItemListDataBinding mBinding) {
            super(mBinding.getRoot());
            imageView = mBinding.ivItem;
            textView = mBinding.tvItem;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            callBack.enviar(itemList.get(getLayoutPosition()));

        }
    }
    public interface PassData{
        void enviar (Item item);
    }




}
