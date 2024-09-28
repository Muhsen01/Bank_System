package com.example.npcbank;
import java.util.ArrayList;

public interface DataPersistenceRetrieval
{
    void persist(ArrayList<Object> list, String fileName);
    ArrayList<Object> retrieve(String fileName);

}
