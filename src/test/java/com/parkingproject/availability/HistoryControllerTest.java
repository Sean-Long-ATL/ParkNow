package com.parkingproject.availability;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HistoryControllerTest
{
    @Autowired
    private HistoryController historyController;

    @MockBean
    private deckHistoryRepository deckHistoryRepository;

    @MockBean
    DeckRepository deckRepository;

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
    public void addHistory() throws Exception
    {
        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.of(testDeck));
        assertEquals("Saved" , historyController.addHistory(1, 1, 1));
    }

    @Test(expected = deckNotFoundException.class)
    public void deckDoesntExist() throws Exception
    {
        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.of(testDeck));
        assertEquals("Saved" , historyController.addHistory(12, 1, 1));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            historyController.addHistory(12, 1, 1);
        });
        assertEquals("Could not find deck: 12", exception.getMessage());
    }
}