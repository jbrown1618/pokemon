package com.jbrown.pokemon.web.controller;

import com.jbrown.pokemon.service.SpeciesProvider;
import com.jbrown.pokemon.web.dto.SpeciesDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SpeciesDto.Mapper speciesDtoMapper;

    public Set<SpeciesDto> getAllSpecies(Request request, Response response) {
        return speciesProvider.getAllSpecies().stream()
            .map(speciesDtoMapper::toDto)
            .collect(Collectors.toSet());
    }

    public SpeciesDto getSpecies(Request request, Response response) {
        int id = Integer.valueOf(request.params(":id"));
        return speciesDtoMapper.toDto(speciesProvider.getSpecies(id));
    }
}
