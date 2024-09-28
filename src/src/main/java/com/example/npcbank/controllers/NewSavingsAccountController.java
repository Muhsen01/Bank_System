package com.example.npcbank.controllers;

import com.example.npcbank.models.Saving;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewSavingsAccountController
{
    @FXML
    private TextField tfOwnerName;
    @FXML
    private TextField tfAccountNumber;

    @FXML
    protected void onSaveDataClick()
    {
        Saving saving = new Saving(tfOwnerName.getText(), tfAccountNumber.getText(), "email address", 5000,
                .05f, 5000);
        //System.out.println(saving.toString());
    }
}
