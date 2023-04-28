package group.ims;

import group.ims.Models.Inhouse;
import group.ims.Models.Outsourced;
import group.ims.Models.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import static group.ims.Models.Inventory.*;

/**
 * This is the Application class for the program.
 * */
public class Application extends javafx.application.Application {
    public static Stage primaryStage; // a static Stage variable to establish one stage throughout the entire JavaFX application

    /**
     * This function loads test data into the JavaFX application and is called before the launch() function within the main() function.
     * */
    public static void loadTestData(){

        Inhouse monitor = new Inhouse(1001, "Monitor", 1.99, 10, 10, 100, 1);
        Outsourced keyboard = new Outsourced(1002, "Keyboard", 2.99, 5, 5, 25, "ABC Keyboards");

        Inhouse woodFrame = new Inhouse(1003, "Wood Frame", 10.00, 10, 10, 100, 2);
        Outsourced glassPane = new Outsourced(1004, "Glass Pane", 5.50, 5, 1, 20, "XYZ Glass");

        Product computer = new Product(2001, "Computer", 5.00, 3, 1, 10);
        computer.addAssociatedPart(monitor);
        computer.addAssociatedPart(keyboard);

        Product window = new Product(2002, "Window", 7.00, 7, 5, 30);
        window.addAssociatedPart(woodFrame);
        window.addAssociatedPart(glassPane);

        addPart(monitor);
        addPart(keyboard);
        addPart(woodFrame);
        addPart(glassPane);
        addProduct(computer);
        addProduct(window);

    }
    /**
     * This is the override version of the start function typical of a JavaFX application.
     * This function loads the Main form.
     * @throws IOException
     * */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1076, 602);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main function that launches the JavaFX application.
     * */
    public static void main(String[] args) {
        loadTestData();
        launch();
    }
}