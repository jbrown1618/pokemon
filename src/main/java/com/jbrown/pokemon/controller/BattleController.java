package com.jbrown.pokemon.controller;

import com.jbrown.pokemon.model.battle.ReturnBattleState;
import org.springframework.stereotype.Controller;
import spark.Request;
import spark.Response;

@Controller
public class BattleController {

    public ReturnBattleState getNextState (Request request, Response response) {
        return new ReturnBattleState();
    }
}
