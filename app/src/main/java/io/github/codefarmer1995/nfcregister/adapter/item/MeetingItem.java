package io.github.codefarmer1995.nfcregister.adapter.item;

import java.io.Serializable;
import java.sql.Timestamp;

public class MeetingItem implements Serializable{

    private String  moderatorName;
    private Timestamp meetingTime;
    private String title;
    private String content;
    private String moderatorAvatarURL;
    private Boolean status;

    public String getModeratorName() {
        return moderatorName;
    }

    public void setModeratorName(String moderatorName) {
        this.moderatorName = moderatorName;
    }

    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModeratorAvatarURL() {
        return moderatorAvatarURL;
    }

    public void setModeratorAvatarURL(String moderatorAvatarURL) {
        this.moderatorAvatarURL = moderatorAvatarURL;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
