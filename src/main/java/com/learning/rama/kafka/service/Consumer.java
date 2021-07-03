package com.learning.rama.kafka.service;

import com.google.gson.Gson;
import com.learning.rama.kafka.model.Transactions;
import com.learning.rama.kafka.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class Consumer {

    @Autowired
    private Producer producer;

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(@Payload String message, @Header(KafkaHeaders.CORRELATION_ID) byte[] coId) throws IOException {
        Random random = new Random();
        System.out.println("#### -> Consumed message -> " + message + " with CorrelationId :" + new String(coId));
        Gson gson = new Gson();
        UserRequest request = gson.fromJson(message, UserRequest.class);

        request.getAccounts().forEach(accId -> {
            for (int i = 1; i <= random.nextInt(10); i++) {
                //String uuid, String accId, String transId, String amount
                Transactions transactions = new Transactions(request.getUuid(), accId, "TRAN" + (i * random.nextInt(1500)), "" + random.nextInt(5000) * i * 10.2);
                String trMsg = gson.toJson(transactions);
                producer.sendMessage(new String(coId), "transcations", trMsg);
            }
        });
    }
}
