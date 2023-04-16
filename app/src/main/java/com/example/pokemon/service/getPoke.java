package com.example.pokemon.service;

import com.example.pokemon.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getPoke {
    @GET("")
    public Call<Pokemon> searchPokemon(String query);
}
