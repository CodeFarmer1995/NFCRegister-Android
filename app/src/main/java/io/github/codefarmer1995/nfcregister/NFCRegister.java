package io.github.codefarmer1995.nfcregister;

import android.app.Application;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.codefarmer1995.nfcregister.network.BasicService;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by Jack on 2018-03-03.
 */

public class NFCRegister extends Application {

    public static  boolean hasNFC;

    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";

    public static Configurations CONFIGURATIONS;

    private static Uri server;

    public static Uri getServer() {
        return server;
    }

    public static void setServer(Uri server) {
        NFCRegister.server = server;
    }

    public static BasicService SERVICE;

    public static void recreateService() {
        SERVICE = new Retrofit.Builder()
                .baseUrl(NFCRegister.getServer().toString())
                .client(NFCRegister.HTTP_CLIENT)
                .build()
                .create(BasicService.class);
    }

    public static File getStorageDir() {
        File dir = new File(Environment.getExternalStorageDirectory(), "JAViewer/");
        dir.mkdirs();
        return dir;
    }

    public static HttpUrl replaceUrl(HttpUrl url) {
        HttpUrl.Builder builder = url.newBuilder();
        String host = url.url().getHost();
        if (hostReplacements.containsKey(host)) {
            builder.host(hostReplacements.get(host));
            return builder.build();
        }

        return url;
    }

    public static Map<String, String> hostReplacements = new HashMap<>();


    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .url(replaceUrl(original.url()))
                    .header("User-Agent", USER_AGENT)
                    .build();

            return chain.proceed(request);
        }
    })
            .cookieJar(new CookieJar() {
                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url, cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url);
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            })
            .build();


    public static <T> T parseJson(Class<T> beanClass, JsonReader reader) throws JsonParseException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(reader, beanClass);
    }

    public static <T> T parseJson(Class<T> beanClass, String json) throws JsonParseException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, beanClass);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hasNFC = getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
    }
}