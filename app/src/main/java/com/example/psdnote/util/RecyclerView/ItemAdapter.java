package com.example.psdnote.util.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.psdnote.R;
import com.example.psdnote.db.entity.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> mItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        TextView itemAccount;
        TextView itemPassword;

        public ViewHolder (View view)
        {
            super(view);
            itemTitle = (TextView) view.findViewById(R.id.title);
            itemAccount = (TextView) view.findViewById(R.id.account);
            itemPassword = (TextView) view.findViewById(R.id.password);
        }
    }

    public ItemAdapter(List<Item> itemList){
        mItemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemAccount.setText(item.getEmail());
        holder.itemPassword.setText(item.getPassword());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
