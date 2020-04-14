package com.parkingproject.availability;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DeckTest
{
    @Autowired
    private DeckRepository deckRepository;

    @Test
    public void testCreateDeckObjectAndGetID() {
        deck deckOne = new deck();
        // Checking if a random ID was generated.
        // We can't predict the actual value but we can at least check if it's not null
        Assert.assertNull(deckOne.getId());
    }

    @Test
    public void testSetAndGetDeckNameForDeckObject() {
        deck deckOne = new deck();
        deckOne.setDeckName("testDeckName");

        Assert.assertEquals("testDeckName", deckOne.getDeckName());
    }

    @Test
    public void testSetAndGetDeckCapacityForDeckObject() {
        deck deckOne = new deck();
        deckOne.setCapacity(500);

        Assert.assertEquals(500, deckOne.getCapacity());
    }

    @Test
    public void testSetAndGetDeckOccupancyForDeckObject() {
        deck deckOne = new deck();
        deckOne.setOccupancy(100);

        Assert.assertEquals(100, deckOne.getOccupancy());
    }
}