package com.jbrown.pokemon.service;

import com.jbrown.pokemon.domain.battle.Move;

import java.util.Set;

public interface MoveProvider {
    Move getMove(String name);

    Set<Move> getAllMoves();
}
