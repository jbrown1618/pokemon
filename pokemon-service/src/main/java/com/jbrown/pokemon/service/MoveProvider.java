package com.jbrown.pokemon.service;

import com.jbrown.pokemon.core.domain.Move;

import java.util.Set;

public interface MoveProvider {
    Move getMove(String name);

    Set<Move> getAllMoves();
}
