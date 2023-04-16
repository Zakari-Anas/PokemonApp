package com.example.pokemon;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokemon.Adapter.PokemonListAdapter;
import com.example.pokemon.Common.Common;
import com.example.pokemon.Common.ItemOffsetDecoration;
import com.example.pokemon.Model.Pokedex;
import com.example.pokemon.Model.PokemonDev;
import com.example.pokemon.Retrofite.IPokemonDex;
import com.example.pokemon.Retrofite.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPokemon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPokemon extends Fragment {

    IPokemonDex iPokemonDev;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;
    static ListPokemon instance;
    public static ListPokemon getInstance() {
        if(instance == null)
                instance= new ListPokemon();
        return instance;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListPokemon() {
        Retrofit retrofit =RetrofitClient.getInstance() ;
        iPokemonDev = retrofit.create(IPokemonDex.class);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListPokemon.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPokemon newInstance(String param1, String param2) {
        ListPokemon fragment = new ListPokemon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list_pokemon, container, false);
       pokemon_list_recyclerview=(RecyclerView) view.findViewById(R.id.pokemon_list_recyclerview);
       pokemon_list_recyclerview.setHasFixedSize(true);
       pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
       ItemOffsetDecoration itemOffsetDecoration=new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
       pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);
        
       fetchData();
        return view;
    }



    private void fetchData() {
                compositeDisposable.add(iPokemonDev.getListPokemon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Consumer<Pokedex>() {
                         @Override
                         public void accept(Pokedex pokemonDev) throws Exception {
                             Common.commonPokemonList=pokemonDev.getPokemon();
                             PokemonListAdapter adapter = new PokemonListAdapter(getActivity(),Common.commonPokemonList);
                             pokemon_list_recyclerview.setAdapter(adapter);

                         }

                                             })
                                );

    }


}