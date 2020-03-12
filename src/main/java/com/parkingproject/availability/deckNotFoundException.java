package com.parkingproject.availability;

public class deckNotFoundException extends RuntimeException
{
    deckNotFoundException(int id)
    {
        super("Could not find deck: " + id);
    }
}
