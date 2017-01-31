package com.jbrown.pokemon.controllers;

import com.jbrown.pokemon.battle.ReturnBattleState;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

@Component
public class BattleController {

    public ReturnBattleState getNextState (Request request, Response response) {
        return new ReturnBattleState();
    }
}
