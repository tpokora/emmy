package org.tpokora.common.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tpokora.common.model.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Autowired
    protected Environment environment;

    @Async
    public CompletableFuture<String> sendNotification(HttpEntity<String> entity) {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(createInterceptors());

        String firebaseResponse = template.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

    public JSONObject generatePushNotificationJSON(Notification notification) {
        JSONObject jsonObject = new JSONObject();
        if (notification.getTopic() != null) {
            jsonObject.put("to",  "/topics/" + notification.getTopic());
        } else {
            jsonObject.put("to",  environment.getProperty("firebase.client.token"));
        }


        HashMap<String, String> data = notification.getData();
        if (data != null) {
            jsonObject.put("data", data);
        } else {
            JSONObject notificationJson = new JSONObject();
            notificationJson.put("title", notification.getTitle());
            notificationJson.put("body", notification.getText());
            jsonObject.put("notification", notificationJson);
        }

        return jsonObject;
    }

    private ArrayList<ClientHttpRequestInterceptor> createInterceptors() {
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        //464584673875
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + environment.getProperty("firebase.server.key")));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        return interceptors;
    }


}
