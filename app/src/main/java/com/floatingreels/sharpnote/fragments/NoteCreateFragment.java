package com.floatingreels.sharpnote.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.model.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteCreateFragment extends Fragment {

    private TextView titleTV, contentTV;
    private FloatingActionButton saveBtn;


    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NoteViewModel noteViewModel = new ViewModelProvider(getParentFragment()).get(NoteViewModel.class);
            noteViewModel.createNote(new Note(titleTV.getText().toString(), contentTV.getText().toString()));
            Navigation.findNavController(view).navigateUp();
        }
    };

    public NoteCreateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_note_create, container, false);



        titleTV = rootView.findViewById(R.id.tv_create_title);
        contentTV = rootView.findViewById(R.id.tv_create_content);

        saveBtn = rootView.findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(saveListener);
        return rootView;
    }
}
