package com.example.simpletodo;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
// displays data from model into a row in the recycler view
public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int position);
    }
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
   List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

   public itemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

       return new ViewHolder(todoView);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
       String item = items.get(position);
       holder.bind(item);
    }

    public int getItemCount(){
        return items.size();
    }
    //Container to provide easy access to views that represent each row of the List
    class ViewHolder extends RecyclerView.ViewHolder{
       TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());

                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //notify what was longpressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
