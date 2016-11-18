package com.jbrown.pokemon.controllers;

import com.jbrown.pokemon.battle.ReturnBattleState;
import spark.Request;
import spark.Response;
import spark.Route;

public class BattleController {

    public static Route getNextState = (Request request, Response response) -> new ReturnBattleState();
}
