package io.github.codefarmer1995.nfcregister;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import io.github.codefarmer1995.nfcregister.adapter.item.DataSource;
import io.github.codefarmer1995.nfcregister.adapter.item.Meeting;

/**
 * Created by Jack on 2018-03-03.
 */

public class Configurations {
    private static File file;

    private ArrayList<Meeting> starred_meetings;

    private DataSource data_source;

    public DataSource getDataSource() {
        return data_source;
    }

    public void setDataSource(DataSource data_source) {
        this.data_source = data_source;
    }

    public ArrayList<Meeting> getStarred_meetings() {
        if(starred_meetings == null)
            return new ArrayList<Meeting>();
        return starred_meetings;
    }

    public void setStarred_meetings(ArrayList<Meeting> starred_meetings) {
        this.starred_meetings = starred_meetings;
    }

    public static Configurations load(File file) {
        Configurations.file = file;
        Configurations config = null;
        try {
            config = NFCRegister.parseJson(Configurations.class, new JsonReader(new FileReader(file)));
        } catch (Exception ignored) {
        }

        if (config == null) {
            config = new Configurations();
        }

        return config;
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(file);
            new Gson().toJson(this, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
