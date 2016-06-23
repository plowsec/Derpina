package derpina;

import derpina.controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //ExecutorService executor = Executors.newFixedThreadPool(1);
        Urls.load();
//        Parent root = FXMLLoader.load(getClass().getResource("/derpina/HomeScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/derpina/HomeScreen.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Derpina");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
