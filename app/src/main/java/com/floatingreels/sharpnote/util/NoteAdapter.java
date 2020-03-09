package com.floatingreels.sharpnote.util;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.floatingreels.sharpnote.R;
import com.floatingreels.sharpnote.model.Note;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> implements Filterable {

    //inner class
    class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTV;
        final TextView contentTV;
        final MaterialButton detailBtn;

        final View.OnClickListener detailListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //in welke card werd op button geklikt
                int position = getAdapterPosition();
                //bundle aanmaken om data in te steken
                Bundle data = new Bundle();
                //welke gegevens moeten er in de bundle
                data.putSerializable("passedNote", filteredItems.get(position));
                //navcontroller werkt voor de view die aangeklikt is
                //wat is de bestemming van navigatie en wat wordt meegegeven
                Navigation.findNavController(view).navigate(R.id.noteToDetail, data);
            }
        };

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            contentTV = itemView.findViewById(R.id.tv_content);
            detailBtn = itemView.findViewById(R.id.btn_detail);
            detailBtn.setOnClickListener(detailListener);
        }
    }

    //fields
    private AppCompatActivity activity;
    private List<Note> filteredItems;
    private List<Note> originalItems;

    //constructor met activity want nodig om te deleten!
    public NoteAdapter(AppCompatActivity activity) {
        filteredItems = new ArrayList<>();
        originalItems = new ArrayList<>();
        this.activity = activity;
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
        Note currentNote = filteredItems.get(position);
        holder.titleTV.setText(currentNote.getTitle());
        holder.contentTV.setText(currentNote.getContent());
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    public void addItems (List<Note> notes){
        filteredItems.clear();
        filteredItems.addAll(notes);
        originalItems.clear();
        originalItems.addAll(notes);
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //wat gebruiker typt omzetten naar string
                String input = constraint.toString();
                //als niets getypt wordt, mag alles weergegeven worden
                if (input.isEmpty()){
                    filteredItems = originalItems;
                } else {
                    filteredItems = originalItems;
                    //voorlopige lijst aanmaken
                    ArrayList<Note> tempList = new ArrayList<>();
                    //loopen over de verzameling
                    for (Note element : filteredItems) {
                        if (element.getTitle().contains(constraint) || element.getContent().contains(constraint)) {
                            tempList.add(element);
                        }
                    }
                    filteredItems = tempList;
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //voer methodes van de viewholder opnieuw uit met de gefilterde data
                notifyDataSetChanged();
            }
        };
    }
}
