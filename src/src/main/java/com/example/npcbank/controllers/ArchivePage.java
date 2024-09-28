package com.example.npcbank.controllers;

import com.example.npcbank.CSVFileAccess;
import com.example.npcbank.DBDataAccess;
import com.example.npcbank.GenericSerFileAccess;
import com.example.npcbank.models.SharedDataModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.nio.file.spi.FileTypeDetector;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArchivePage
{
    @FXML
    TextField tfFileName;

    enum FileType
    {
        CSV, SER, DB
    }

    private FileType fileType;

    @FXML private Button btnArchiveData;


    public void onArchiveDataClick(ActionEvent actionEvent)
    {

        ArrayList<Object> accounts = SharedDataModel.getInstance().getData();
        String fileName = tfFileName.getText();

        if (fileType == FileType.SER)
        {
            GenericSerFileAccess genFile = new GenericSerFileAccess();
            genFile.persist(accounts, fileName);
        }
        else if (fileType == FileType.CSV)
        {
            CSVFileAccess csvFile = new CSVFileAccess();
            csvFile.persist(accounts, fileName);
        }
        else if (fileType == FileType.DB)
        {
            DBDataAccess db = new DBDataAccess();
            Thread persistThread = new Thread(() ->
            {
                try
                {
                    db.persist(accounts);
                }
                catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            });
            persistThread.start();

            Thread checkStatusThread = new Thread(()-> {
                while (!SharedDataModel.getInstance().isThreadCompleted())
                {
                    Platform.runLater(()->btnArchiveData.setText(Integer.toString(SharedDataModel.getInstance().getPercentCompleted())));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if ( SharedDataModel.getInstance().isThreadCompleted())
                {
                    Platform.runLater(()->btnArchiveData.setText("Archive Completed"));
                }
            });
            checkStatusThread.start();
        }
    }


    public void onChooseFileType(ActionEvent actionEvent)
    {
        RadioButton rb = (RadioButton) actionEvent.getSource();
        if (rb.getId().equals("rbCSV")) {
            fileType = FileType.CSV;
        } else if (rb.getId().equals("rbSERIAL")) {
            fileType = FileType.SER;
            ;
        } else if (rb.getId().equals("rbDB")) {
            fileType = FileType.DB;
            ;
        }

    }
}
