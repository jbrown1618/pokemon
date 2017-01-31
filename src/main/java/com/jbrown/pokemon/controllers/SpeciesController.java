package com.jbrown.pokemon.controllers;

import com.jbrown.pokemon.dto.SpeciesDto;
import com.jbrown.pokemon.service.SpeciesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import spark.Request;
import spark.Response;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class SpeciesController extends BaseController {

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
