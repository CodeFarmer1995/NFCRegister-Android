package io.github.codefarmer1995.nfcregister.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.codefarmer1995.nfcregister.adapter.item.Meeting;
import io.github.codefarmer1995.nfcregister.view.decoration.MeetingItemDecoration;
import io.github.javiewer.adapter.MovieAdapter;

import io.github.javiewer.network.provider.AVMOProvider;
import io.github.javiewer.view.decoration.MovieItemDecoration;
import io.github.javiewer.view.listener.EndlessOnScrollListener;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import okhttp3.ResponseBody;
import retrofit2.Call;

public abstract class MeetingFragment extends RecyclerFragment<Meeting, LinearLayoutManager> {

    public MeetingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        this.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.addItemDecoration(new MeetingItemDecoration());
        this.setAdapter(new SlideInBottomAnimationAdapter(new MovieAdapter(getItems(), this.getActivity())));
        RecyclerView.ItemAnimator animator = new SlideInUpAnimator();
        animator.setAddDuration(300);
        mRecyclerView.setItemAnimator(animator);

        this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOnScrollListener().refresh();
            }
        });

        this.addOnScrollListener(new EndlessOnScrollListener<Movie>() {
            @Override
            public Call<ResponseBody> newCall(int page) {
                return MeetingFragment.this.newCall(page);
            }

            @Override
            public RecyclerView.LayoutManager getLayoutManager() {
                return MeetingFragment.this.getLayoutManager();
            }

            @Override
            public SwipeRefreshLayout getRefreshLayout() {
                return MeetingFragment.this.mRefreshLayout;
            }

            @Override
            public List<Movie> getItems() {
                return MeetingFragment.this.getItems();
            }

            @Override
            public RecyclerView.Adapter getAdapter() {
                return MeetingFragment.this.getAdapter();
            }

            @Override
            public void onResult(ResponseBody response) throws Exception {
                super.onResult(response);
                List<Movie> wrappers = AVMOProvider.parseMovies(response.string());

                int pos = getItems().size();

                getItems().addAll(wrappers);
                getAdapter().notifyItemRangeInserted(pos, wrappers.size());
            }
        });

        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                getOnRefreshListener().onRefresh();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    public abstract Call<ResponseBody> newCall(int page);
}
