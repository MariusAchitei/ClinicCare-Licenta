package ro.uaic.clinic_care.service;

import ro.uaic.clinic_care.dto.PokemonDto;
import ro.uaic.clinic_care.dto.PokemonResponse;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemonId(int id);
}
