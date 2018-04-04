package io.github.codefarmer1995.nfcregister.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.codefarmer1995.nfcregister.adapter.item.Meeting;
import io.github.javiewer.R;
import io.github.javiewer.activity.MovieActivity;
import io.github.javiewer.adapter.item.Movie;

/**
 * Project: JAViewer
 */
public class MovieAdapter extends ItemAdapter<Meeting, MovieAdapter.ViewHolder> {

    private Activity mParentActivity;

    public MovieAdapter(List<Meeting> meetings, Activity mParentActivity) {
        super(meetings);
        this.mParentActivity = mParentActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = getItems().get(position);

        holder.parse(meeting);

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mParentActivity, MovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                intent.putExtras(bundle);

                mParentActivity.startActivity(intent);
            }
        });

        holder.mImageCover.setImageDrawable(null);
        Glide.with(holder.mImageCover.getContext().getApplicationContext())
                .load(movie.getCoverUrl())
                .into(holder.mImageCover);

        holder.mImageHot.setVisibility(movie.isHot() ? View.VISIBLE : View.GONE);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_title)
        public TextView mTextTitle;

        @BindView(R.id.movie_size)
        public TextView mTextCode;

        @BindView(R.id.movie_date)
        public TextView mTextDate;

        @BindView(R.id.movie_cover)
        public ImageView mImageCover;

        @BindView(R.id.movie_hot)
        public ImageView mImageHot;

        @BindView(R.id.card_movie)
        public CardView mCard;

        public void parse(Meeting meeting) {
            mTextCode.setText(meeting.getCode());
            mTextTitle.setText(meeting.getTitle());
            mTextDate.setText(meeting.getDate());
        }

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
