package com.floatingreels.sharpnote.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.model.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.time.LocalDate;

public class NoteCreateFragment extends Fragment {

    private EditText titleET, contentET;
    private FloatingActionButton saveFAB;
    private Note passedNote;

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Note note = new Note(titleET.getText().toString(), contentET.getText().toString());
            note.setTimeModified(LocalDate.now());
            NoteViewModel noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
            noteViewModel.createNote(note);
            Navigation.findNavController(view).navigateUp();
        }
    };

    public NoteCreateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_note_create, container, false);
        titleET = rootView.findViewById(R.id.et_create_title);
        contentET = rootView.findViewById(R.id.et_create_content);
        saveFAB = rootView.findViewById(R.id.fab_note_save);
        saveFAB.setOnClickListener(saveListener);

        //vang de doorgestuurde argumenten op
        Bundle passedData = getArguments();
        //kijken of er gegevens zijn doorgegeven
        if (passedData != null) {
            //controleren of de juiste gegevens zijn doorgegeven
            if (passedData.containsKey("editNote")){
                //haal de juiste serializable eruit en cast het naar een note
                Note passedNote = (Note) passedData.getSerializable("editNote");
                //vul de input tekstvelden in met de fields uit de note
                //
                titleET.setText(passedNote.getTitle(), TextView.BufferType.EDITABLE);
                contentET.setText(passedNote.getContent(), TextView.BufferType.EDITABLE);
            }
        }
        return rootView;
    }
}
