package com.parkingproject.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class deckUpdate
{
    @Autowired
    private DeckRepository deckRepository;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler()
    {
        System.out.println("message received");
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException
            {
                String response[] = message.getPayload().toString().split(" ");
                Integer delta = Integer.parseInt(response[1]);
                Integer id = Integer.parseInt(response[0]);
                deck dbDeck = deckRepository.findById(id).orElseThrow(() -> new deckNotFoundException(id));
                dbDeck.setOccupancy(dbDeck.getOccupancy() + delta);
                deckRepository.save(dbDeck);

                System.out.println(delta);
            }

        };
    }
}
