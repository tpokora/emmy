package org.tpokora.common.services;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tpokora.common.model.Notification;
import org.tpokora.config.properties.FirebaseProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String TO = "to";
    private static final String DATA = "data";
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final String NOTIFICATION = "notification";

    private FirebaseProperties firebaseProperties;

    public AndroidPushNotificationsService(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
    }

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
            jsonObject.put(TO,  "/topics/" + notification.getTopic());
        } else {
            jsonObject.put(TO,  firebaseProperties.getValue(FirebaseProperties.CLIENT_TOKEN));
        }


        HashMap<String, String> data = notification.getData();
        if (data != null) {
            jsonObject.put(DATA, data);
        } else {
            JSONObject notificationJson = new JSONObject();
            notificationJson.put(TITLE, notification.getTitle());
            notificationJson.put(BODY, notification.getText());
            jsonObject.put(NOTIFICATION, notificationJson);
        }

        return jsonObject;
    }

    private ArrayList<ClientHttpRequestInterceptor> createInterceptors() {
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebaseProperties.getValue(FirebaseProperties.SERVER_KEY)));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        return interceptors;
    }


}
