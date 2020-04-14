package com.parkingproject.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/update")
public class updateController
{
    @Autowired
    private DeckRepository deckRepository;

    @PutMapping(path = "/{id}")
    public @ResponseBody String updateDeck(@PathVariable int id, @RequestParam Integer delta)
    {
        deck dbDeck = deckRepository.findById(id).orElseThrow(() -> new deckNotFoundException(id));
        dbDeck.setOccupancy(dbDeck.getOccupancy() + delta);
        deckRepository.save(dbDeck);
        return "Confirmed, current occupancy is now: " + dbDeck.getOccupancy();
    }
}
