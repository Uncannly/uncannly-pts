package com.example;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.ivona.services.tts.IvonaSpeechCloudClient;
import com.ivona.services.tts.model.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
class PtsService {
    InputStream create(String word) {
        return new ByteArrayInputStream( word.getBytes() );
    }

    InputStream createOld(String word) {
        IvonaSpeechCloudClient speechCloud = new IvonaSpeechCloudClient(
                new ClasspathPropertiesFileCredentialsProvider("resources/IvonaCredentials.properties")
        );
        speechCloud.setEndpoint("https://tts.eu-west-1.ivonacloud.com");

        IvonaCloud ivonaCloud = new IvonaCloud();
        ivonaCloud.

        Voice voice = new Voice();
        voice.setName("Salli");

        Parameters parameters = new Parameters();
        parameters.setRate("slow");

        Input input = new Input();
        input.setType("application/ssml+xml");
        input.setData(String.format(
                "<speak><phoneme alphabet=\"ipa\" ph=\"%s\">_</phoneme></speak>\"", word
        ));


        CreateSpeechRequest createSpeechRequest = new CreateSpeechRequest();
        createSpeechRequest.setVoice(voice);
        createSpeechRequest.setInput(input);
        createSpeechRequest.setParameters(parameters);

        CreateSpeechResult createSpeechResult = speechCloud.createSpeech(createSpeechRequest);
        return createSpeechResult.getBody();
    }
}
