package hu.nye.progtech.torpedo.service.repository;

import java.util.List;

import hu.nye.progtech.torpedo.model.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for player.
 */

public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findAll();
}
