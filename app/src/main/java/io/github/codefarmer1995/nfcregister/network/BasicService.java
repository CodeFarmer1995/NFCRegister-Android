package io.github.codefarmer1995.nfcregister.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jack on 2018-03-03.
 */

public interface BasicService {
    String context= "/NFC-Sign-In";
    @GET(BasicService.context + "/meetings/{page}")
    Call<ResponseBody> getMeetings(@Path("page") int page);

}
