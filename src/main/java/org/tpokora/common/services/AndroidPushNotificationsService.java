package org.tpokora.common.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
            jsonObject.put("to",  "/topics/" + notification.getTopic());
        } else {
            jsonObject.put("to",  firebaseProperties.getClientToken());
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
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebaseProperties.getServerKey()));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        return interceptors;
    }


}
