package com.example.pokemonrawgithub.Model;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {

    private ArrayList<Pokemon> pokemon;

    public Pokedex() {
    }

    public Pokedex(ArrayList<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
