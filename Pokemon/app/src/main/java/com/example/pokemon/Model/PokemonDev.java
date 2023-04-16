package com.example.pokemon.Model;

import java.util.List;

public class PokemonDev {
    private List<Pokemon> pokemon;

    public PokemonDev() {
    }

    public PokemonDev(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}

