package com.study.eureka.client.sentence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Controller
public class SentenceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * Display a small list of Sentences to the caller:
     */
    @GetMapping("/sentence")
    public @ResponseBody String getSentence() {
        return
                "<h3>Some Sentences</h3><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>" +
                        buildSentence() + "<br/><br/>"
                ;
    }

    /**
     * Assemble a sentence by gathering random words of each part of speech:
     */
    public String buildSentence() {
        String sentence = "There was a problem assembling the sentence!";
        try{
            sentence =
                    String.format("%s %s %s %s %s.",
                            getWord("CLIENT-SUBJECT"),
                            getWord("CLIENT-VERB"),
                            getWord("CLIENT-ARTICLE"),
                            getWord("CLIENT-ADJECTIVE"),
                            getWord("CLIENT-NOUN") );
        } catch ( Exception e ) {
            System.out.println(e);
        }
        return sentence;
    }

    /**
     * Obtain a random word for a given part of speech, where the part
     * of speech is indicated by the given service / client ID:
     */
    public String getWord(String service) {
        List<ServiceInstance> list = discoveryClient.getInstances(service);
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri !=null ) {
                return (new RestTemplate()).getForObject(uri,String.class);
            }
        }
        return null;
    }
}
