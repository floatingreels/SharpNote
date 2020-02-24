package com.floatingreels.sharpnote.util;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    //inner class
    class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTV;
        final TextView contentTV;
        final Button detailBtn;

//        final View.OnClickListener detailListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        };

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            contentTV = itemView.findViewById(R.id.tv_content);
            detailBtn = itemView.findViewById(R.id.btn_detail);
//            detailBtn.setOnClickListener(detailListener);
        }
    }

    //fields
    private ArrayList<Note> items;

    //constructor
    public NoteAdapter() {
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //waarbinnen wordt alles getekend?
        Context context = parent.getContext();
        //layout inflater opvragen
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //de view zelf inflaten binnen deze parent
        View card = layoutInflater.inflate(R.layout.note_card, parent, false);

        return new NoteViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = items.get(position);
        holder.titleTV.setText(currentNote.getTitle());
        holder.contentTV.setText(currentNote.getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems (ArrayList<Note> notes){
        items.clear();
        items.addAll(notes);
    }
}
