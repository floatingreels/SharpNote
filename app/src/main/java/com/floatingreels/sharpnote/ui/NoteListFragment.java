package com.floatingreels.sharpnote.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.viewmodel.NoteViewModel;
import com.floatingreels.sharpnote.util.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {

    private NoteAdapter noteAdapter;
    private FloatingActionButton createFAB;

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            //
            noteAdapter.getFilter().filter(newText);
            return false;
        }
    };

    private View.OnClickListener createListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Navigation.findNavController(view).navigate(R.id.listToCreate);
        }
    };

    //constructor zonder parameters
    public NoteListFragment() {
    }

    //view van dit fragment inflaten door xml in res\layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_list, container, false);
        //menu van de activity toevoegen aan fragment
        setHasOptionsMenu(true);
        //verwijzing naar recyclerview via ID
        RecyclerView notesRV = rootView.findViewById(R.id.rv_note_list);
        //op welke manier gaat recycler view opgevuld worden
        notesRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        //nieuwe instantie aanmaken van adapter om gegevens om te zetten in cards
        noteAdapter = new NoteAdapter((AppCompatActivity) getActivity());
        //juiste recycler view linken aan de juiste adapter
        notesRV.setAdapter(noteAdapter);
        //verwijzing naar view model, waar staan alle gegevens en in welke klasse
        //dit fragment is verantwoordelijk voor klasse met de gegevens
        NoteViewModel noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        //observeren voor potentiële wijzigingen
        //kan enkel als scherm in het geheugen bestaat
        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Note>>(){
            @Override
            public void onChanged(ArrayList<Note> notes) {
                noteAdapter.addItems(notes);
            }
        });
        //floating action button linken aan component in UI via ID
        createFAB = rootView.findViewById(R.id.fab_note_create);
        //de juiste listener instellen voor floatingactionbutton
        createFAB.setOnClickListener(createListener);
        //view van fragment weergeven
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        //searchview zoeken in menu item, moet gedowncast worden naar searchview
        SearchView searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
        //searchview koppelen aan de listener
        searchView.setOnQueryTextListener(searchListener);
        super.onCreateOptionsMenu(menu, inflater);

    }
}
