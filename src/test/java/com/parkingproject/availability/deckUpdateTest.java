package com.parkingproject.availability;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
public class deckUpdateTest
{
    @Autowired
    deckUpdate du;

    @MockBean
    DeckRepository deckRepository;

    GenericMessage<?> message = new GenericMessage<>("1 45");

    private deck testDeck = new deck();

    @Before
    public void setUp()
    {
        //mockMvc = MockMvcBuilders.standaloneSetup(historyController).setControllerAdvice(new deckNotFoundAdvice()).build();

        testDeck.setId(1);
        testDeck.setDeckName("K Deck");
        testDeck.setCapacity(1200);
        testDeck.setOccupancy(1000);
    }

    @Test
    public void testMessage()
    {
        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(testDeck));
        du.handler().handleMessage(message);
        Mockito.verify(deckRepository).save(any(deck.class));
    }

    @Test(expected = deckNotFoundException.class)
    public void testMessageInvalidDeckList()
    {
        //Mockito.when(deckRepository.findById(2)).thenReturn(java.util.Optional.ofNullable(testDeck));
        du.handler().handleMessage(message);
    }

    @Test
    public void testMessageZeroDelta()
    {
        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(testDeck));
        GenericMessage<?> zero = new GenericMessage<>("1 0");
        du.handler().handleMessage(zero);
        Mockito.verify(deckRepository).save(any(deck.class));
    }

    @Test(expected = NumberFormatException.class)
    public void testMessageLargeNumber()
    {
        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(testDeck));
        GenericMessage<?> large = new GenericMessage<>("1 2147483649");
        du.handler().handleMessage(large);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNoDelta()
    {
        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(testDeck));
        GenericMessage<?> large = new GenericMessage<>("1");
        du.handler().handleMessage(large);
    }
}