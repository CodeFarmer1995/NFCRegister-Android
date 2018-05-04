package io.github.codefarmer1995.nfcregister.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.github.codefarmer1995.nfcregister.R;
import io.github.codefarmer1995.nfcregister.fragment.MeetingListFragment;
import io.github.codefarmer1995.nfcregister.view.ViewUtil;

public class MeetingListActivity extends AppCompatActivity {

    public static Intent newIntent(Context context, String title, String link) {
        Intent intent = new Intent(context, MeetingListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("link", link);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);

        Bundle bundle = this.getIntent().getExtras();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(bundle.getString("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(ViewUtil.dpToPx(4));

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            MeetingListFragment fragment = new MeetingListFragment();
            fragment.setArguments(bundle);
            transaction.replace(R.id.content_query, fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
