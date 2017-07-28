package com.jbrown.pokemon.web.controller;

import com.jbrown.pokemon.core.domain.ReturnBattleState;
import org.springframework.stereotype.Controller;
import spark.Request;
import spark.Response;

@Controller
public class BattleController {

    public ReturnBattleState getNextState(Request request, Response response) {
        return new ReturnBattleState();
    }
}
