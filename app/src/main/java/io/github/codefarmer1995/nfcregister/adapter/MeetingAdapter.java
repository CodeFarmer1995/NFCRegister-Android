package io.github.codefarmer1995.nfcregister.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.codefarmer1995.nfcregister.R;
import io.github.codefarmer1995.nfcregister.activity.MeetingActivity;
import io.github.codefarmer1995.nfcregister.adapter.item.Meeting;
import io.github.codefarmer1995.nfcregister.adapter.item.MeetingItem;


public class MeetingAdapter extends ItemAdapter<Meeting, MeetingAdapter.ViewHolder> {

    private Activity mParentActivity;

    public MeetingAdapter(List<MeetingItem> meetings, Activity mParentActivity) {
        super(meetings);
        this.mParentActivity = mParentActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_meeting, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MeetingItem meetingItem = getItems().get(position);

        holder.parse(meetingItem);

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mParentActivity, MeetingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("meeting", meetingItem);
                intent.putExtras(bundle);

                mParentActivity.startActivity(intent);
            }
        });

        holder.mImageModerator.setImageDrawable(null);
        Glide.with(holder.mImageModerator.getContext().getApplicationContext())
                .load(meetingItem.getModeratorAvatarURL())
                .into(holder.mImageModerator);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_meeting_title)
        public TextView mTextTitle;

        @BindView(R.id.tv_meeting_content)
        public TextView mTextContent;

        @BindView(R.id.tv_meeting_date)
        public TextView mTextDate;

        @BindView(R.id.iv_user_avatar)
        public ImageView mImageModerator;

        @BindView(R.id.tv_status)
        public TextView mTextStatus;

        @BindView(R.id.status_color)
        public ImageView mImageStatus;

        @BindView(R.id.card_meeting)
        public CardView mCard;

        public void parse(MeetingItem meetingItem) {
            mTextContent.setText(meetingItem.getContent());
            mTextTitle.setText(meetingItem.getTitle());
            mTextDate.setText(meetingItem.getMeetingTime().toString());

            if (meetingItem.getStatus()) {
                mTextStatus.setText(R.string.meeting_registered);
                mImageStatus.setImageTintList(ColorStateList.valueOf(Color.parseColor("#008000")));

            } else if (meetingItem.getMeetingTime().getTime() > new Date().getTime()){
                mTextStatus.setText(R.string.meeting_absence);
                mImageStatus.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            }else {
                mTextStatus.setText(R.string.meeting_registering);
                mImageStatus.setImageTintList(ColorStateList.valueOf(Color.parseColor("#808080")));
            }
        }

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
