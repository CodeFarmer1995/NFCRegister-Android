package io.github.codefarmer1995.nfcregister;

import android.net.Uri;

/**
 * Created by Jack on 2018-03-03.
 */

public class Properties {
    private String latest_version;
    private int latest_version_code;

    private String changelog;
    private String server;

    public Properties() {
    }

    public String getLatestVersion() {
        return latest_version;
    }

    public int getLatestVersionCode() {
        return latest_version_code;
    }

    public String getServer() {
        return server;
    }

    public String getChangelog() {
        return changelog;
    }
}
