package com.example.mynotes.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynotes.AddNote;
import com.example.mynotes.R;
import com.example.mynotes.model.Notes;
import com.example.mynotes.sqlite.DbHelper;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    public static final String ID_KEY = "id";
    public static final String TITLE_KEY = "title";
    public static final String NOTE_KEY = "note";
    public static final String ACTION_KEY = "action";

    List<Notes> notesList;
    Context context;

    DbHelper dbHelper;

    public NoteAdapter(List<Notes> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;

        dbHelper = new DbHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.row_notes, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int itemPosition) {
        viewHolder.setNoteItems(notesList.get(itemPosition));
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showMenu(itemPosition);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvNote = itemView.findViewById(R.id.tv_note);

        }

        void setNoteItems(Notes notes) {
            tvTitle.setText(notes.getTitle());
            tvNote.setText(notes.getNote());
        }
    }

    public void showMenu(final int itemPosition) {
        String[] items = {"Update", "Delete"};

        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setTitle("Menu")
                .setNegativeButton("Batal", null)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intentUpdate = new Intent(context, AddNote.class);
                            intentUpdate.putExtra(ID_KEY, notesList.get(itemPosition).getId());
                            intentUpdate.putExtra(TITLE_KEY, notesList.get(itemPosition).getTitle());
                            intentUpdate.putExtra(NOTE_KEY, notesList.get(itemPosition).getNote());
                            intentUpdate.putExtra(ACTION_KEY, 1);
                            context.startActivity(intentUpdate);
                        } else {
                            deleteUser(notesList.get(itemPosition).getId());
                        }
                    }
                });

        alert.show();
    }

    public void deleteUser(String id) {
        dbHelper.deleteUser(id);
    }

}
