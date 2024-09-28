package com.example.npcbank;
import java.io.*;
import java.util.ArrayList;

//Serialize/Deserialize ArrayList to/from File
public class GenericSerFileAccess
{
    public void persist(ArrayList<Object> objects, String filename)
    {
        try (
                FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)
        )
        {
            objectOut.writeObject(objects);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Object> retrieve(String filename)
    {
        ArrayList<Object> objects = new ArrayList<>();
        try (
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        )
        {
            objects = (ArrayList<Object>) objectIn.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return objects;
    }
}