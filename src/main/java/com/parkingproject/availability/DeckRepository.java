package com.parkingproject.availability;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeckRepository extends CrudRepository<deck, Integer>
{
    Optional<deck> findBydeckName(String deckName);
    List<deck> findAllBydeckName(String deckName);
}
