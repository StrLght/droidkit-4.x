package droidkit.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * @author Daniel Serdyukov
 */
interface SQLiteSchema {

    void onCreate(@NonNull SQLiteDatabase db);

    void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion);

}
