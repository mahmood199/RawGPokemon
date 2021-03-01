package com.example.pokemonrawgithub;

import com.example.pokemonrawgithub.Model.Pokedex;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("pokedex.json")
    Call<Pokedex> obtainList();
}
