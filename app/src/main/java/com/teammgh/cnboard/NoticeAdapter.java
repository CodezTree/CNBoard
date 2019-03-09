package com.teammgh.cnboard;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
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
        ImageView iv_target_grade;
        TextView tv_notice_title;
        TextView tv_notice_date;
        CardView cv_card;

        NoticeViewHolder(View view) {
            super(view);
            tv_notice_title = view.findViewById(R.id.tv_notice_title);
            iv_notice = view.findViewById(R.id.iv_notice);
            tv_notice_date = view.findViewById(R.id.tv_notice_date);
            cv_card = view.findViewById(R.id.cv_card);
            iv_target_grade = view.findViewById(R.id.iv_target_grade);
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

        noticeViewHolder.tv_notice_date.setText(noticeDataList.get(position).notice_date);
        noticeViewHolder.tv_notice_title.setText(noticeDataList.get(position).notice_title);

        Log.d("test", "label setting");

        noticeViewHolder.iv_target_grade.setVisibility(View.VISIBLE); // For reuse
        switch(noticeDataList.get(position).target_grade) {
            case 0:
                // NULL
                noticeViewHolder.iv_target_grade.setVisibility(View.INVISIBLE);
                break;
            case 1:
                noticeViewHolder.iv_target_grade.setImageResource(R.drawable.tag_r);
                break;
            case 2:
                noticeViewHolder.iv_target_grade.setImageResource(R.drawable.tag_g);
                break;
            case 3:
                noticeViewHolder.iv_target_grade.setImageResource(R.drawable.tag_b);
                break;
            case 4:
                // ALL GRADE
                noticeViewHolder.iv_target_grade.setVisibility(View.INVISIBLE);
                break;
        }


        noticeViewHolder.iv_notice.postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.get().load("http://45.32.49.247:8000/notice/" +  noticeDataList.get(position).notice_image).into(noticeViewHolder.iv_notice);
                //noticeDataList.get(position).imageURL
                //https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png

            }
        }, 1);

        noticeViewHolder.cv_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoticeDetailActivity.class);

                intent.putExtra("URL", noticeDataList.get(position).notice_image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeDataList.size();
    }
}
