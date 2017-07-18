package com.jbrown.pokemon.service;

import com.jbrown.pokemon.domain.battle.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ConfiguredMoveProvider implements MoveProvider {
    @Autowired
    private Set<Move> allMoves;

    @Override
    public Move getMove(String name) {
        return allMoves.stream()
            .filter(move -> move.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No move exists with name " + name));
    }

    @Override
    public Set<Move> getAllMoves() {
        return allMoves;
    }
}
