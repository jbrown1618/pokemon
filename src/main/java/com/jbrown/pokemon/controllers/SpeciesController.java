package com.jbrown.pokemon.controllers;

import com.jbrown.pokemon.dto.SpeciesDto;
import com.jbrown.pokemon.enums.Species;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class SpeciesController extends Controller {
    private static SpeciesDto.Mapper mapper = new SpeciesDto.Mapper();

    public static Route getAllSpecies = (Request request, Response response) -> {
        List<SpeciesDto> speciesDtos = new ArrayList<>();
        for (Species species : Species.values()) {
            SpeciesDto dto = mapper.toDto(species);
            speciesDtos.add(dto);
        }
        return speciesDtos;
    };

    public static Route getSpecies = (Request request, Response response) -> {
        int id = Integer.valueOf(request.params(":id"));
        for (Species species : Species.values()) {
            if (species.getNumber() == id) {
                return mapper.toDto(species);
            }
        }
        response.status(404);
        return "Species not found";
    };
}
