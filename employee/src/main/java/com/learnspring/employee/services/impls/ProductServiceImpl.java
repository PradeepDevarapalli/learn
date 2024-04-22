package com.learnspring.employee.services.impls;

import com.learnspring.employee.entity.Product;
import com.learnspring.employee.kafka.kafkaevent.ProductCreatedEvent;
import com.learnspring.employee.services.interfaces.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Cacheable("products")
    public String createProductAsynchronously(Product product) throws Exception {
        String productID = UUID.randomUUID().toString();

        //TODO: persist details to DB before publishing the event.

        ProductCreatedEvent productEvent  = new ProductCreatedEvent(productID,
                product.getTitle(),
                product.getPrice(),
                product.getQuantity());

        // send message asynchronously
        CompletableFuture<SendResult<String,ProductCreatedEvent>> future = kafkaTemplate.send("product-created-events-topic", productID, productEvent);
        future.whenComplete((result, exception) -> {
           if(exception != null) {
            log.error("Exception while sending message to Topic" +exception.getMessage());
           }
           else {
            log.info("Message sent Successfully " +result.getRecordMetadata());
           }
        });

        // blocks the current thread until the future is completed. (Synchronous)
        //future.join();

        // send message synchronously
        //SendResult<String,ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic", productID, productEvent).get();

        return productID;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true, beforeInvocation = true)
    public String createProductSynchronously(Product product) throws Exception {
        String productID = UUID.randomUUID().toString();

        //TODO: persist details to DB before publishing the event.

        ProductCreatedEvent productEvent  = new ProductCreatedEvent(productID,
                product.getTitle(),
                product.getPrice(),
                product.getQuantity());

        // send message synchronously
        // blocks the current thread until the future is completed. (Synchronous)
        //future.join();

        SendResult<String,ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic", productID, productEvent).get();
        result.getRecordMetadata();
        result.getProducerRecord();

        return productID;
    }
}
