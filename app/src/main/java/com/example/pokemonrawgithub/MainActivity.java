package com.example.pokemonrawgithub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.pokemonrawgithub.Model.Pokedex;
import com.example.pokemonrawgithub.Model.Pokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV = findViewById(R.id.textView);
        TV.setText("");
        ExampleThread thread = new ExampleThread();
        thread.start();

    }

    public class ExampleThread extends Thread{
        @Override
        public void run() {
            super.run();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2)
                    .build();

            PokeApi service = retrofit.create(PokeApi.class);
            Call<Pokedex> PRC = (Call<Pokedex>) service.obtainList();

            PRC.enqueue(new Callback<Pokedex>() {
                @Override
                public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {

                    if(response.isSuccessful()) {
                        Log.i("API called Successfully", "onResponse: "+response.body().toString());
                        //TV.setText(response.body().toString());

                        System.out.println("Response CAptured");

                        Pokedex pokedex = response.body();
                        ArrayList<Pokemon> list = pokedex.getPokemon();

                        for (Pokemon lists : list) {
                            String content = "";
                            content += "NAME : " + lists.getName() + "\n";
                            content += "Number : " + lists.getNum() + "\n";
                            content += "ID : " + lists.getId() + "\n";
                            content += "Image URL : " + lists.getImg() + "\n";
                            content += "Height : " + lists.getHeight() + "\n";
                            content += "Weight : " + lists.getWeight() + "\n";
                            content += "Candy : " + lists.getCandy() + "\n";
                            content += "Spawn_Chance : " + lists.getSpawn_chance() + "\n\n\n";

                            TV.append(content);
                        }
                    }
                    else
                        Log.i("API call failed", "onResponse: ");
                }

                @Override
                public void onFailure(Call<Pokedex> call, Throwable t) {
                    System.out.println("Response failed");
                }
            });


        }
    }
}