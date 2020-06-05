package com.yerti;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;


import javax.rmi.CORBA.Util;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Label pathbox;

    private File selectedDirectory;

    @FXML
    public void onExitClicked(javafx.scene.input.MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void onConfirmClicked(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("clicked on confirm");

        if (selectedDirectory == null) {
            return;
        }

        File factorioParent = new File(System.getenv("APPDATA") + "/Factorio");
        File selectedDirectoryParent = selectedDirectory.getParentFile();
        File factorioPath = new File(System.getenv("APPDATA") + "/Factorio/mods");

        String originalModName = "mods";
        String originalSelectedDirectoryName = selectedDirectory.getName();

        factorioPath.renameTo(new File(factorioPath.getParent() + "\\" + selectedDirectory.getName()));
        selectedDirectory.renameTo(new File(selectedDirectory.getParent() + "\\mods"));


        try {
            System.out.println(factorioParent.getAbsolutePath() + "/" + originalSelectedDirectoryName);
            System.out.println(selectedDirectoryParent.getAbsolutePath());
            Files.move(new File(factorioParent.getAbsolutePath() + "/" + originalSelectedDirectoryName).toPath(), new File(selectedDirectoryParent.getAbsolutePath()+"/" + originalSelectedDirectoryName).toPath());
            Files.move(new File(selectedDirectoryParent.getAbsolutePath() + "/mods").toPath(), new File(factorioParent.getAbsolutePath() + "/mods").toPath());
            //Files.move(new File(selectedDirectoryParent.getAbsolutePath() + "/mods").toPath(), factorioParent.toPath());
            //Files.move(new File(System.getenv("APPDATA") + "/Factorio/" + originalSelectedDirectoryName).toPath(), new File(selectedDirectory.getParentFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Utils.moveFiles(selectedDirectory, factorioPath.getParentFile());


    }

    @FXML
    public void onSelectModpackClicked(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("clicked on select");

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Factorio Modpack Location");
        File defaultDirectory = new File("C:/");
        chooser.setInitialDirectory(defaultDirectory);
        selectedDirectory = chooser.showDialog(FactorioModpackSwitcher.getStage());
        pathbox.setText(selectedDirectory.getAbsolutePath());

    }
}
