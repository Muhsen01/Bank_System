package com.example.npcbank;
import com.example.npcbank.models.Checking;

import java.io.*;
import java.util.ArrayList;

//CSV File Access Class (to/from an ArrayList of Objects)
public class CSVFileAccess
{

    public void persist(ArrayList<Object> data, String filename)
    {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for (Object checking : data)
            {
                writer.write(checking.toString());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Checking> retrieve(String filename)
    {
        ArrayList<Checking> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                accounts.add(CSVtoChecking(line));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return accounts;
    }

    Checking CSVtoChecking (String line)
    {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            String name = parts[0].trim();
            String number = parts[1].trim();
            String email = parts[2].trim();
            double balance = Double.parseDouble(parts[3].trim());
            String overDraftAccount = parts[4].trim();
            int overDraftLimit = Integer.parseInt(parts[5].trim());
            String owningBranch = parts[6].trim();
            return new Checking(name, number, email, balance, overDraftAccount, overDraftLimit, owningBranch );
        }
        else
        {
            return null;
        }
    }
}
