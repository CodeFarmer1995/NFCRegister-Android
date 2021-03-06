package io.github.codefarmer1995.nfcregister.fragment;

import io.github.javiewer.JAViewer;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Project: JAViewer
 */
public class ReleasedFragment extends MeetingFragment {
    @Override
    public Call<ResponseBody> newCall(int page) {
        return JAViewer.SERVICE.getReleased(page);
    }
}
