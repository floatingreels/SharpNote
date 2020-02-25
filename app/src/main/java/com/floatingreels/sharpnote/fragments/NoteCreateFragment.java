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

public class NoteCreateFragment extends Fragment {

    private EditText titleET, contentET;
    private FloatingActionButton saveBtn;

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Note note = new Note(titleET.getText().toString(), contentET.getText().toString());
            NoteViewModel noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
            noteViewModel.createNote(note);
            Navigation.findNavController(view).navigate(R.id.createToList);
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

        saveBtn = rootView.findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(saveListener);
        return rootView;
    }
}
