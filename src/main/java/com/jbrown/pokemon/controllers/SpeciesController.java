package com.jbrown.pokemon.controllers;

import com.jbrown.pokemon.enums.Species;
import spark.Request;
import spark.Response;
import spark.Route;

public class SpeciesController extends Controller {

    public static Route getAllSpecies = (Request request, Response response) -> Species.values();
}
