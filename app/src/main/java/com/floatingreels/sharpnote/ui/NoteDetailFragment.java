package com.floatingreels.sharpnote.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.threeten.bp.format.DateTimeFormatter;

public class NoteDetailFragment extends Fragment {

    private TextView titleTV, contentTV, dateCreatedTV, dateModifiedTV;
    private FloatingActionButton editFAB;
    private Note receivedNote;
    private View.OnClickListener editListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //bundle aanmaken om data in te steken
            Bundle data = new Bundle();
            //de gegevens in de bundle zetten (zelfde gegevens die werden ontvangen)
            data.putSerializable("editNote", receivedNote);
            //navigatie starten en gegevens mee doorgeven
            //TODO: skip fragment in backstack. nav graph?
            Navigation.findNavController(view).navigate(R.id.detailToCreate, data);
        }
    };

    public NoteDetailFragment() {
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_detail, container, false);
        //verwijzing naar componenten in UI
        titleTV = rootView.findViewById(R.id.tv_detail_title);
        contentTV = rootView.findViewById(R.id.tv_detail_content);
        dateCreatedTV = rootView.findViewById(R.id.tv_detail_datecreated);
        dateModifiedTV = rootView.findViewById(R.id.tv_detail_datemodified);
        editFAB = rootView.findViewById(R.id.fab_note_edit);
        editFAB.setOnClickListener(editListener);

        //bundle opvragen die vanuit de adapter werd meegegeven als argument
        Bundle reveivedData = getArguments();

        if (reveivedData != null) {
            if (reveivedData.containsKey("passedNote")){
                receivedNote = (Note) reveivedData.getSerializable("passedNote");

                titleTV.setText(receivedNote.getTitle());
                contentTV.setText(receivedNote.getContent());
                dateCreatedTV.setText(receivedNote.getTimeCreated().format(org.threeten.bp.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                dateModifiedTV.setText(receivedNote.getTimeModified().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }
        return rootView;
    }
}
