package com.floatingreels.sharpnote.ui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.floatingreels.sharpnote.viewmodel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.threeten.bp.LocalDate;

public class NoteCreateFragment extends Fragment {

    private EditText titleET, contentET;
    private FloatingActionButton saveFAB;
    private NoteViewModel noteViewModel;
    private Note passedNote;
    private Bundle passedData;

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!dataPassed()) {
                Note note = new Note(titleET.getText().toString(), contentET.getText().toString());
                noteViewModel.createNote(note);
                Navigation.findNavController(view).navigateUp();
            } else {
                passedNote.setTitle(titleET.getText().toString());
                passedNote.setContent(contentET.getText().toString());
                noteViewModel.updateNote(passedNote);
                Navigation.findNavController(view).navigateUp();
            }
        hideKeyboardFrom(getActivity(), getView().getRootView());
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
        noteViewModel  = new ViewModelProvider(getActivity()).get(NoteViewModel.class);

        if (dataPassed()){
            //haal de juiste serializable eruit en cast het naar een note
            passedNote = (Note) passedData.getSerializable("editNote");
            //vul de input tekstvelden in met de fields uit de note
            titleET.setText(passedNote.getTitle(), TextView.BufferType.EDITABLE);
            contentET.setText(passedNote.getContent(), TextView.BufferType.EDITABLE);

        }
        return rootView;
    }

    public boolean dataPassed(){
        passedData = getArguments();
        //kijken of er gegevens zijn doorgegeven
        if (passedData != null) {
            //controleren of de juiste gegevens zijn doorgegeven
            if (passedData.containsKey("editNote"))
                return true;
        }
        return false;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
