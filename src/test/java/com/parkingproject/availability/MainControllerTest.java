package com.parkingproject.availability;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest
{

    @Autowired
    private MainController mainController;

    @MockBean
    DeckRepository deckRepository;

    private MockMvc mockMvc;

    private deck testDeck = new deck();

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController)
                .setControllerAdvice(new deckNotFoundAdvice()).build();

        testDeck.setDeckName("K Deck");
        testDeck.setCapacity(1200);
        testDeck.setOccupancy(1000);
    }

    @Test
    public void getDeck() throws Exception
    {

        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.of(testDeck));

        mockMvc.perform(MockMvcRequestBuilders.get("/decklist/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getNoDeck() throws Exception
    {

        Mockito.when(deckRepository.findById(1)).thenReturn(java.util.Optional.of(testDeck));

        mockMvc.perform(MockMvcRequestBuilders.get("/decklist/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
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
    public void addDeckNoName() throws Exception
    {

        mockMvc.perform(MockMvcRequestBuilders.post("/decklist/add")
                .param("capacity", "2300")
                .param("occupancy", "1200"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addDeckNoCapacity() throws Exception
    {

        mockMvc.perform(MockMvcRequestBuilders.post("/decklist/add")
                .param("name", "K Deck")
                .param("capacity", "2300"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addDeckNoOccupancy() throws Exception
    {

        mockMvc.perform(MockMvcRequestBuilders.post("/decklist/add")
                .param("name", "K Deck")
                .param("occupancy", "1200"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void addDeckNotANumber() throws Exception
    {

        mockMvc.perform(MockMvcRequestBuilders.post("/decklist/add")
                .param("name", "K Deck")
                .param("capacity", "asdf")
                .param("occupancy", "qwerty"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void getAllDecks() throws Exception
    {
        //Mockito.when(deckRepository.findAll()).thenReturn(deckRepository.findAll());

        mockMvc.perform(MockMvcRequestBuilders.get("/decklist/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.deckName", Matchers.is("T_Deck")));
    }
}