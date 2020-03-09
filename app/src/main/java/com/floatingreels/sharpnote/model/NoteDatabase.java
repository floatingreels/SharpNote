package com.floatingreels.sharpnote.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.floatingreels.sharpnote.model.util.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = {Note.class})
@TypeConverters({Converters.class})
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase sharedInstance;

    public static NoteDatabase getSharedInstance(Context context){
        if (sharedInstance == null){
            buildDatabase(context);
        }
        return sharedInstance;
    }

    private static void buildDatabase(Context context) {
        sharedInstance = Room.databaseBuilder(
                context,
                NoteDatabase.class,
                "note_database"
                ).build();
    }

    public abstract NoteDAO getNoteDAO();

    public static ExecutorService databaseExecutor = Executors.newFixedThreadPool(3);
}
