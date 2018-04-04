package io.github.codefarmer1995.nfcregister.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.util.List;


import io.github.codefarmer1995.nfcregister.Configurations;
import io.github.codefarmer1995.nfcregister.NFCRegister;
import io.github.codefarmer1995.nfcregister.Properties;

import io.github.codefarmer1995.nfcregister.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        checkPermissions();

    }

    public void readProperties() {
        Request request = new Request.Builder()
                .url("https://raw.githubusercontent.com/CodeFarmer1995/NFCRegister-Android/master/properties.json")
                .build();
        NFCRegister.HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("NETWORK", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Properties properties = NFCRegister.parseJson(Properties.class, response.body().string());
                Log.i("PRO", properties.toString());
                if (properties != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            handleProperties(properties);
                        }
                    });
                }
            }
        });
    }

    public void handleProperties(Properties properties) {

        NFCRegister.setServer(Uri.parse(properties.getServer()));
        //NFCRegister.setServer(Uri.parse("http://10.0.2.2:8080/"));

        int currentVersion;
        try {
            currentVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Hacked???");
        }

        if (properties.getLatestVersionCode() > 0 && currentVersion < properties.getLatestVersionCode()) {

            String message = "新版本：" + properties.getLatestVersion();
            if (properties.getChangelog() != null) {
                message += "\n\n更新日志：\n\n" + properties.getChangelog() + "\n";
            }

            final boolean[] update = {false};
            final AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("发现更新")
                    .setMessage(message)
                    .setNegativeButton("忽略更新", null)
                    .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            update[0] = true;
                        }
                    })
                    .create();
            dialog.show();

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    start();
                    if (update[0]) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/CodeFarmer1995/NFCRegister-Android/releases")));
                    }
                }
            });
        } else {
            start();
        }

    }

    public void start() {
        startActivity(new Intent(StartActivity.this, LoginActivity.class));
        finish();
    }

    public void readFile() {
        Toast.makeText(StartActivity.this, StartActivity.this.getExternalFilesDir(null).toString(), Toast.LENGTH_LONG);
        File oldConfig = new File(StartActivity.this.getExternalFilesDir(null), "configurations.json");
        File config = new File(NFCRegister.getStorageDir(), "configurations.json");
        if (oldConfig.exists()) {
            oldConfig.renameTo(config);
        }

        File noMedia = new File(NFCRegister.getStorageDir(), ".nomedia");
        try {
            noMedia.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NFCRegister.CONFIGURATIONS = Configurations.load(config);

        readProperties();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Dexter.withActivity(StartActivity.this)
                    .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            if (!NFCRegister.hasNFC)
                                new AlertDialog.Builder(StartActivity.this)
                                        .setTitle("无NFC功能")
                                        .setCancelable(false)
                                        .setMessage("您的设备不支持NFC功能，无法签到！")
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                checkPermissions();

                                            }
                                        })
                                        .show();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            new AlertDialog.Builder(StartActivity.this)
                                    .setTitle("权限申请")
                                    .setCancelable(false)
                                    .setMessage("NFCRegister 需要储存空间权限，储存用户配置。请您允许。")
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            checkPermissions();

                                        }
                                    })
                                    .show();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    })
                    .onSameThread()
                    .check();
            return;
        }

        readFile();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
