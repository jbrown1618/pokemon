package com.jbrown.pokemon.web.dto;

public interface DtoMapper<T, S> {
    public S toDto(T t);
}
