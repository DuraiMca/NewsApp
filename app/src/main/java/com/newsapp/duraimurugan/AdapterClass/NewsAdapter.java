package com.newsapp.duraimurugan.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.duraimurugan.ModelClass.NewsResponse;
import com.newsapp.duraimurugan.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewholder> {
    Context context;
    ArrayList<NewsResponse.ArticleResponse> newsResponses = new ArrayList<>();
    NewsClick newsClick;

    public void setNewsClick(NewsClick newsClick) {
        this.newsClick = newsClick;
    }

    public NewsAdapter(Context context, ArrayList<NewsResponse.ArticleResponse> newsResponses) {
        this.context = context;
        this.newsResponses = newsResponses;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
       Picasso.get().load(newsResponses.get(position).getUrlToImage()).into(holder.mImageViewNewsImg);
        holder.mTextViewTitle.setText("" + newsResponses.get(position).getTitle());

        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(newsResponses.get(position).getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("yyyy-MM-dd  hh:mm");
        String newDateStr = postFormater.format(dateObj);


        holder.mTextViewDate.setText("" +newDateStr );


    }

    @Override
    public int getItemCount() {
        return newsResponses.size();
    }

    public void updateList(ArrayList<NewsResponse.ArticleResponse> articleResponses,Context context) {
        this.newsResponses = articleResponses;
        this.context=context;
        notifyDataSetChanged();
    }

    class viewholder extends RecyclerView.ViewHolder {
        ImageView mImageViewNewsImg;
        TextView mTextViewTitle;
        TextView mTextViewDate;
        TextView mTextViewDesc;
        LinearLayout mLinearLayoutMain;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            mImageViewNewsImg = itemView.findViewById(R.id.news_img);
            mTextViewTitle = itemView.findViewById(R.id.news_title);
            mTextViewDate = itemView.findViewById(R.id.news_date);

            mLinearLayoutMain = itemView.findViewById(R.id.layout_main);
            mLinearLayoutMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsClick.getposition(getAdapterPosition());
                }
            });
        }
    }
}
