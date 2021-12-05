package hu.nye.progtech.torpedo.service.util;

import java.util.List;
import java.util.stream.Collectors;

import hu.nye.progtech.torpedo.service.interactions.Interaction;
import org.springframework.stereotype.Service;

/**
 * Class for enable/disable interactions.
 */

@Service
public class InteractionEnabler {


    /**
     * Should disable put.
     *
     * @return {@link Interaction}
     */

    public Interaction disablePut(List<Interaction> interactions, Interaction put) {
        interactions.add(put);
        interactions.stream()
                .filter(interaction -> interaction.getName().equals("put"))
                .forEach(interaction -> interaction.setUsable(false));
        return interactions.stream().filter(interaction -> interaction.getName().equals("put")).collect(Collectors.toList()).get(0);
    }

    /**
     * Should enable shoot.
     *
     * @return {@link Interaction}
     */

    public Interaction enableShoot(List<Interaction> interactions) {
        interactions.stream()
                .filter(interaction -> interaction.getName().equals("shoot"))
                .forEach(interaction -> interaction.setUsable(true));
        return interactions.stream().filter(interaction -> interaction.getName().equals("shoot")).collect(Collectors.toList()).get(0);

    }
}
