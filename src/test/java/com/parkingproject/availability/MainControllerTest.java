package com.parkingproject.availability;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.parkingproject.availability.DeckRepository;
import com.parkingproject.availability.MainController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest
{
    private TestEntityManager testEntityManager;

    @Autowired
    private MainController mainController;

    @MockBean
    DeckRepository deckRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void getDeck() throws Exception
    {
        deck testDeck = new deck();
        testDeck.setDeckName("K Deck");
        testDeck.setCapacity(1200);
        testDeck.setOccupancy(1000);

        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.of(testDeck));

        mockMvc.perform(MockMvcRequestBuilders.get("/decklist/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void addDeck() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/decklist/add")
                .param("name", "K Deck")
                .param("capacity", "2300")
                .param("occupancy", "1200"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getAllDecks() throws Exception
    {

        mockMvc.perform(MockMvcRequestBuilders.get("/decklist/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.deckName", Matchers.is("T_Deck")));
    }
}