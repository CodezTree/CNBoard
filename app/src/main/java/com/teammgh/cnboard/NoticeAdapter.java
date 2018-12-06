package com.teammgh.cnboard;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_notice;
        TextView tv_notice_time;
        CardView cv_card;

        NoticeViewHolder(View view) {
            super(view);
            iv_notice = view.findViewById(R.id.iv_notice);
            tv_notice_time = view.findViewById(R.id.tv_notice_time);
            cv_card = view.findViewById(R.id.cv_card);
        }
    }


    private ArrayList<NoticeData> noticeDataList;

    NoticeAdapter(ArrayList<NoticeData> noticeDataList, Context context) {
        this.noticeDataList = noticeDataList; // 참조 ㅇㅇ
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_layout, parent, false);

        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;

        noticeViewHolder.tv_notice_time.setText(noticeDataList.get(position).uploadTime);

        noticeViewHolder.iv_notice.postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.get().load("http://10.1.111.142/notice/" +  noticeDataList.get(position).imageURL).into(noticeViewHolder.iv_notice);
                //noticeDataList.get(position).imageURL
                //https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
            }
        }, 100);

        noticeViewHolder.cv_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoticeDetailActivity.class);

                intent.putExtra("URL", noticeDataList.get(position).imageURL);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeDataList.size();
    }
}
