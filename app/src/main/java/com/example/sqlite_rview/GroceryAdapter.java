package com.example.sqlite_rview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;

    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView countText;


        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.tv_name_item);
            countText = itemView.findViewById(R.id.tv_amount_item);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.grocery_item, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
      @SuppressLint("Range") String name = mCursor.getString(mCursor.getColumnIndex(MyConstants.DbConstants.COLUMN_NAME));
        @SuppressLint("Range") int amount = mCursor.getInt(mCursor.getColumnIndex(MyConstants.DbConstants.COLUMN_AMOUNT));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
