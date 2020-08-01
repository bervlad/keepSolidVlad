package com.example.app.database;

import android.net.Uri;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;

public class TypesConverter {

    @TypeConverter
    public String fromArray(ArrayList<String> authorsArray) {
        StringBuilder authorsString= new StringBuilder();

        if (authorsArray!=null && authorsArray.size()>0) {
            int i = 0;
            for (String s : authorsArray) {
                if (i < authorsArray.size() - 1) {
                    authorsString.append(s + ", ");
                } else authorsString.append(s);
                i++;
            }
        } else return null;
        return authorsString.toString();
    }

    @TypeConverter
    public ArrayList <String> toArray(String authorsString) {
        ArrayList<String> authorsArray;
        if (authorsString!=null) {
        authorsArray = new ArrayList<String>(
                Arrays.asList(authorsString.split(", "))
        ); } else return null;
        return authorsArray;
    }

    @TypeConverter
    public String fromUri(Uri uri) {
        return uri.toString();
    }

    @TypeConverter
    public Uri toUri(String uriString) {
        return Uri.parse(uriString);
    }

}
