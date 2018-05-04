package io.github.codefarmer1995.nfcregister.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.github.codefarmer1995.nfcregister.NFCRegister;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Project: JAViewer
 */
public class MeetingListFragment extends MeetingFragment {

    public String link;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        this.link = bundle.getString("link");
    }

    @Override
    public Call<ResponseBody> newCall(int page) {
        return NFCRegister.SERVICE.get(this.link + "/page/" + page);
    }
}
