package com.example.win.easy.domain;

import androidx.room.TypeConverter;

import com.example.win.easy.song.DataSource;

import java.util.ArrayList;
import java.util.List;

public class CustomTypeConverters {

    @TypeConverter
    public static List<Character> string2characterList(String string){
        List<Character> characterList=new ArrayList<>();
        if(string!=null){
            int length=string.length();
            for (int index=0;index<length;index++)
                characterList.add(string.charAt(index));
        }
        return characterList;
    }

    @TypeConverter
    public static String characterList2string(List<Character> characterList){
        StringBuilder string= new StringBuilder();
        if(characterList!=null)
            for (Character character:characterList)
                string.append(character);
        return string.toString();
    }

    @TypeConverter
    public static DataSource string2dataSource(String string){
        return string==null?null:DataSource.valueOf(string);
    }

    @TypeConverter
    public static String dataSource2string(DataSource dataSource){
        return dataSource==null?null:dataSource.toString();
    }
}
