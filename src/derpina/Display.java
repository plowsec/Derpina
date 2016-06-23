package derpina;


import javafx.scene.control.Alert;

public class Display {

    public static void error(String message){
        error(message, null);
    }

    public static void error(String message, String title){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Erreur");
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);

        errorAlert.showAndWait();
    }
}
