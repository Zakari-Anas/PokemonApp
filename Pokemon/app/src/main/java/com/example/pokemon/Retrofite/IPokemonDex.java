package com.example.pokemon.Retrofite;

//import android.database.Observable;
import io.reactivex.Observable;

import com.example.pokemon.Model.Pokedex;
import com.example.pokemon.Model.PokemonDev;

import retrofit2.http.GET;

public interface IPokemonDex {
    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
