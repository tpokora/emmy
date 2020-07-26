package org.tpokora.common.model;

import org.tpokora.weather.model.Warning;

import java.util.HashMap;

public class Notification {
    private String topic;
    private String title;
    private String text;

    private HashMap<String, String> data;

    public Notification() {}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public static Notification createNotificationFromWarning(Warning warning) {
        Notification notification = new Notification();
        notification.title = String.format("%s WARNING, Level: %d", warning.getName(), warning.getLevel());
        notification.text = String.format("From: %s, To: %s",
                warning.getPeriod().getFromString(),
                warning.getPeriod().getToString());
        return notification;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "topic='" + topic + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", data=" + data +
                '}';
    }
}
