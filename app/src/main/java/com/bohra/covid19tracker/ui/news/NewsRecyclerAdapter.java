package com.bohra.covid19tracker.ui.news;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bohra.covid19tracker.R;

import com.bohra.covid19tracker.data.SearchNews.SearchNewsArticle;
import com.bohra.covid19tracker.uitil.DateAndTimeFormat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.Search_VH> {

    List<SearchNewsArticle> newsArticles;

    public NewsRecyclerAdapter(List<SearchNewsArticle> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public Search_VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_item, viewGroup, false);
        return new Search_VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Search_VH search_vh, int i) {

        SearchNewsArticle article = newsArticles.get(i);
        search_vh.author.setText(article.getAuthor());
        search_vh.Title.setText(article.getTitle());
        search_vh.description.setText(article.getDescription());
        search_vh.source.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .into(search_vh.image);
        //Setting date format to check how to convert date and time format Check DateAndTimeFormat Class
        search_vh.date.setText(DateAndTimeFormat.DateFormat(article.getPublishedAt()));
        search_vh.time.setText(" \u2022 " + DateAndTimeFormat.DateToTimeFormat(article.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }


    public class Search_VH extends RecyclerView.ViewHolder {

        ConstraintLayout NewsLayout;
        ImageView image;
        TextView author, date, Title, description, source, time;

        public Search_VH(@NonNull final View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            author = (TextView) itemView.findViewById(R.id.author);
            date = (TextView) itemView.findViewById(R.id.date);
            Title = (TextView) itemView.findViewById(R.id.Title);
            description = (TextView) itemView.findViewById(R.id.desc);
            source = (TextView) itemView.findViewById(R.id.source);
            time = (TextView) itemView.findViewById(R.id.time);
            NewsLayout = (ConstraintLayout) itemView.findViewById(R.id.NewsLayout);

            NewsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String Url = newsArticles.get(getAdapterPosition()).getUrl();

                    Intent intent = new Intent(itemView.getContext(), NewsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("URL", Url);
                    intent.putExtras(bundle);

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
