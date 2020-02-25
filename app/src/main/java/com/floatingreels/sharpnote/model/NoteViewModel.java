package com.floatingreels.sharpnote.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.floatingreels.sharpnote.fragments.NoteCreateFragment;

import java.util.ArrayList;

public class NoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Note>> notes;
    private ArrayList<Note> allNotes;


    //CREATE
    private void loadNotes(){
        allNotes = new ArrayList<>();
        allNotes.add(new Note("To-do list", "find job\nget rich\ndie trying"));
        allNotes.add(new Note("Zakdoek Raketkanon","afmetingen 39 x 39 cm, misschien kader kopen in Lucas Creativ?"));
        allNotes.add(new Note("Deurbel", "nieuwe deurbel gaan kopen in Gamma of Brico"));
        notes.setValue(allNotes);
    }

    public void createNote(Note note){
        allNotes.add(note);
    }

    //READ
    public MutableLiveData<ArrayList<Note>> getAllNotes() {
        if (notes == null){
            notes = new MutableLiveData<>();
            loadNotes();
        }
        return notes;
    }

    //DELETE

}
