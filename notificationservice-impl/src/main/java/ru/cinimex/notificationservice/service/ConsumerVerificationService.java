package ru.cinimex.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.cinimex.notificationservice.domain.EmailMessage;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ConsumerVerificationService {

    private final EmailSenderService emailSenderService;
    private final Logger LOGGER = LoggerFactory.getLogger(ConsumerVerificationService.class);

    @KafkaListener(topics = "email-topic", groupId = "email-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeUserMessage(@Payload EmailMessage msg, @Headers MessageHeaders headers) throws IOException {
        System.out.println("received data in Consumer One ="+ msg.getBody());
        emailSenderService.sendConfirmationMessage(msg);
    }

}
