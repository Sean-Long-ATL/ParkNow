package com.parkingproject.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class homepageController
{
    @Autowired
    private DeckRepository deckRepository;

    @GetMapping("/")
    public @ResponseBody
    Iterable<deck> getAllDecks()
    {
        // This returns a JSON or XML with the users
        return deckRepository.findAll();
    }
}
