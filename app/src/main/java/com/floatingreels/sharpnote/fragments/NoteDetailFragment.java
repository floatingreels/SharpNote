package com.floatingreels.sharpnote.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.format.DateTimeFormatter;

public class NoteDetailFragment extends Fragment {

    private TextView titleTV, contentTV, dateModiefiedTV;

    public NoteDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke_detail, container, false);
        //verwijzing naar componenten in UI
        titleTV = rootView.findViewById(R.id.tv_detail_title);
        contentTV = rootView.findViewById(R.id.tv_detail_content);
        dateModiefiedTV = rootView.findViewById(R.id.tv_detail_date);

        //bundle opvragen die vanuit de adapter werd meegegeven als argument
        Bundle data = getArguments();

        if (data != null) {
            if (data.containsKey("passedNote")){
                Note note = (Note) data.getSerializable("passedNote");

                titleTV.setText(note.getTitle());
                contentTV.setText(note.getContent());
                dateModiefiedTV.setText(note.getTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        }

        return rootView;
    }

}
