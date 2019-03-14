//package com.teammgh.cnboard;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView username;
//        TextView chatcontent;
//
//        public MyViewHolder(@NonNull View view) {
//            super(view);
////            username = view.findViewById(R.id. =----);
////            chatcontent = view.findViewById(R.id. ---);
//        }
//    }
//
//    private ArrayList<ChatItem> chatItems;
//
//    public ChatAdapter(ArrayList<ChatItem> chatItems) {
//        this.chatItems = chatItems;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//       // View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.------, parent, false);
//        //return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        MyViewHolder myViewHolder = (MyViewHolder) holder;
//
//        myViewHolder.username.setText(chatItems.get(position).getUsername());
//        myViewHolder.chatcontent.setText(chatItems.get(position).getChatcontent());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return chatItems.size();
//    }
//}
