package com.parkingproject.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class homepageController
{
    @Autowired
    private DeckRepository deckRepository;

    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<deck> getAllDecks()
    {
        // This returns a JSON or XML with the users
        return deckRepository.findAll();
    }
}
