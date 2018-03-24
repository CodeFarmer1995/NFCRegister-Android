package io.github.codefarmer1995.nfcregister.network;

import io.github.codefarmer1995.nfcregister.beans.LoginToken;
import io.github.codefarmer1995.nfcregister.beans.RegisterRequestBody;
import io.github.codefarmer1995.nfcregister.beans.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jack on 2018-03-03.
 */

public interface BasicService {
    String context = "/NFCRegister";

    @GET(BasicService.context + "/meetings/{page}")
    Call<ResponseBody> getMeetings(@Path("page") int page);

    @POST(BasicService.context + "/login")
    Call<ResponseBody> login(@Body LoginToken loginToken);

    @POST(BasicService.context + "/register")
    Call<ResponseBody> singup(@Body RegisterRequestBody body);

}
