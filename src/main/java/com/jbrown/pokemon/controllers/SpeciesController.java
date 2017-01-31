package com.jbrown.pokemon.controllers;

import com.jbrown.pokemon.dto.SpeciesDto;
import com.jbrown.pokemon.service.SpeciesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SpeciesController extends Controller {

    @Autowired
    private SpeciesProvider speciesProvider;

    @Autowired
    private SpeciesDto.Mapper mapper;

    public Set<SpeciesDto> getAllSpecies(Request request, Response response) {
        return speciesProvider.getAllSpecies().stream()
            .map(mapper::toDto)
            .collect(Collectors.toSet());
    }

    public SpeciesDto getSpecies(Request request, Response response) {
        int id = Integer.valueOf(request.params(":id"));
        return mapper.toDto(speciesProvider.getSpecies(id));
    }
}
