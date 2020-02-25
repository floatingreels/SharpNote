package com.floatingreels.sharpnote.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.SearchView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.model.NoteViewModel;
import com.floatingreels.sharpnote.util.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {
    //field
    private NoteAdapter noteAdapter;
    private FloatingActionButton createBtn;

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
        noteAdapter = new NoteAdapter();
        //juiste recycler view linken aan de juiste adapter
        notesRV.setAdapter(noteAdapter);
        //verwijzing naar view model, waar staan alle gegevens en in welke klasse
        //dit fragment is verantwoordelijk voor klasse met de gegevens
        NoteViewModel noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        //observeren voor potentiële wijzigingen
        //kan enkel als scherm in het geheugen bestaat
        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Note>>(){
            @Override
            public void onChanged(ArrayList<Note> notes) {
                noteAdapter.addItems(notes);
            }
        });
        //floating action button linken aan component in UI via ID
        createBtn = rootView.findViewById(R.id.btn_create);
        //de juiste listener instellen voor floatingactionbutton
        createBtn.setOnClickListener(createListener);
        //view van fragment weergeven
        return rootView;
    }
}
