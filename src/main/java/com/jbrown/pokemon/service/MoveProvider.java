package com.jbrown.pokemon.service;

import com.jbrown.pokemon.battle.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MoveProvider {
    @Autowired
    private Set<Move> allMoves;

    public Move getMove(String name) {
        return allMoves.stream()
            .filter(move -> move.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No move exists with name " + name));
    }

    public Set<Move> getAllMoves() {
        return allMoves;
    }
}
