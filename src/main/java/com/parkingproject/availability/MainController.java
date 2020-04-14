package com.parkingproject.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/decklist")
public class MainController
{
    @Autowired
    private DeckRepository deckRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewDeck(@RequestParam String name, @RequestParam Integer capacity, @RequestParam Integer occupancy)
    {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        deck n = new deck();
        n.setDeckName(name);
        n.setCapacity(capacity);
        n.setOccupancy(occupancy);
        deckRepository.save(n);
        return "Saved";
    }

    @PutMapping("/decklist/edit/{id}")
    public Optional<deck> updateDeck(@RequestParam deck newDeck, @PathVariable int id)
    {
        return deckRepository.findById(id).map(deck -> {
            deck.setDeckName(newDeck.getDeckName());
            deck.setCapacity(newDeck.getCapacity());
            deck.setOccupancy(newDeck.getOccupancy()); return deckRepository.save(deck);});
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody deck getDeck(@PathVariable int id)
    {
        return deckRepository.findById(id).orElseThrow(() -> new deckNotFoundException(id));
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<deck> getAllDecks()
    {
        // This returns a JSON or XML with the users
        return deckRepository.findAll();
    }
}
