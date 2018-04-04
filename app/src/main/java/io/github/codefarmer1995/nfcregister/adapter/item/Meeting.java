package io.github.codefarmer1995.nfcregister.adapter.item;


import java.sql.Timestamp;


public class Meeting {
    private int id;
    private Timestamp meetingTime;
    private String location;
    private int moderator;
    private String title;
    private String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public int getModerator() {
        return moderator;
    }

    public void setModerator(int moderator) {
        this.moderator = moderator;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meeting meeting = (Meeting) o;

        if (id != meeting.id) return false;
        if (moderator != meeting.moderator) return false;
        if (meetingTime != null ? !meetingTime.equals(meeting.meetingTime) : meeting.meetingTime != null) return false;
        if (location != null ? !location.equals(meeting.location) : meeting.location != null) return false;
        if (title != null ? !title.equals(meeting.title) : meeting.title != null) return false;
        if (content != null ? !content.equals(meeting.content) : meeting.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (meetingTime != null ? meetingTime.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + moderator;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
