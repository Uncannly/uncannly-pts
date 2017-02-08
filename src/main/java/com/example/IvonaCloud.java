package com.example;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.ivona.services.tts.IvonaSpeechCloudClient;
import com.ivona.services.tts.model.CreateSpeechRequest;

import java.io.InputStream;

class IvonaCloud {
    private IvonaSpeechCloudClient client;

    IvonaCloud() {
        this.client = new IvonaSpeechCloudClient(
                new ClasspathPropertiesFileCredentialsProvider("resources/IvonaCredentials.properties")
        );

        this.client.setEndpoint("https://tts.eu-west-1.ivonacloud.com");
    }

    public InputStream createSpeechInputStream(CreateSpeechRequest request) {
        return this.client.createSpeech(request).getBody();
    }

}
