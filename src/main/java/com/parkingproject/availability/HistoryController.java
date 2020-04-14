package com.parkingproject.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class HistoryController
{
    @Autowired
    private deckHistoryRepository deckHistoryRepository;

    @Autowired
    private DeckRepository deckRepository;

    @Scheduled(cron = "0 */15 * * * *")
    public void updateHistory()
    {
        Iterable<deck> dlist = deckRepository.findAll();
        for(deck d: dlist)
        {
            addHistory(d.getId());
        }
        //addHistory(1);
    }

    //@PostMapping(path = "/add") // Map ONLY POST Requests
    public
    String addHistory(Integer deckId)
    {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Date creation = new Date();

        deck db_deck = deckRepository.findById(deckId).orElseThrow(() -> new deckNotFoundException(deckId));
        deckHistory n = new deckHistory();
        n.setDeckId(db_deck.getId());
        n.setSpaceLeft(db_deck.getCapacity() - db_deck.getOccupancy());
        //n.setTime()
        deckHistoryRepository.save(n);
        return "Saved";
    }

    //@GetMapping(path = "/all")
    public @ResponseBody
    Iterable<deckHistory> getAllHistory()
    {
        return deckHistoryRepository.findAll();
    }
}
