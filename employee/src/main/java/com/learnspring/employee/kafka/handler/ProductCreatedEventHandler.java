package com.learnspring.employee.kafka.handler;

import com.learnspring.employee.exception.kafka.NotRetryableException;
import com.learnspring.employee.exception.kafka.RetryableException;
import com.learnspring.employee.kafka.kafkaevent.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Component
@KafkaListener(topics="product-created-event-topic")
public class ProductCreatedEventHandler {

    RestTemplate restTemplate;


    WebClient webClient = WebClient.create();
    public ProductCreatedEventHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @KafkaHandler
    public void handle(ProductCreatedEvent productCreatedEvent) {
        try {
            log.info("Received a new event: " + productCreatedEvent.getProductID());
        }
        catch (Exception e) {
            throw new NotRetryableException("An Error took place, no need to try this message again");
        }
    }

    @KafkaHandler
    public void reTryableDemo(ProductCreatedEvent productCreatedEvent) {
        String requestURL = "http://someURL.com";

        try {
            ResponseEntity<String> response = restTemplate.exchange(requestURL, HttpMethod.GET,null,String.class);

            if(response.getStatusCode().value() == HttpStatus.OK.value()) {
                log.info("Received response. " +response.getBody());
            }
        } catch (ResourceAccessException e) {
            throw  new RetryableException("An Error took place, try this message again");
        } catch (HttpServerErrorException he) {
            throw new NotRetryableException(he);
        }
    }
    public int usingStreams(int[] nums) {
       int[] distinct =  Arrays.stream(nums).distinct().toArray();
        System.arraycopy(distinct, 0, nums, 0, distinct.length );


        return 0;



    }

    public void exmaple() {

        List<String> abc = new ArrayList<>();
        abc.add("123");
        abc.get(0);
        int[] a = new int[] {1,1,2,2,2,3};
        int l =   a.length;

        Set<Integer> intSet = new HashSet<>();
        intSet.add(1);
        usingStreams(a);

        String s = "abc";
        HashMap<Character, Integer> charFrequency = new HashMap<>();



        webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }
}
