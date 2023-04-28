package group.ims.Controllers;

import group.ims.Models.Inhouse;
import group.ims.Models.Outsourced;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.io.IOException;


import static group.ims.Application.primaryStage;
import static group.ims.Models.Inventory.*;
/**
 * This class controls what occurs within the "Add Part" pane of the GUI.
 * */
public class AddPartController {

    public TextField partID;    // textfield where the partID is entered within the GUI

    public TextField partName;  // textfield where the part name is entered within the GUI

    public TextField part_InventoryLevel; // textfield where the inventory level (stock) is entered within the GUI
    public TextField part_Price; // textfield where the part price is entered within the GUI
    public TextField partMax; // textfield where the part max is entered within the GUI
    public TextField partMin; // textfield where the part min is entered within the GUI
    public TextField toggleField; // textfield where either the company name for an outsourced part or the machine ID for an inhouse part is entered within the GUI
    public Text MachineID_CompanyName; // text that either reads "Machine ID" or "Company Name" depending on whether the "inhouse" or "outsourced" radio button is selected
    public RadioButton inHouseButton; // "inhouse" radio button
    public RadioButton outsourcedButton; // "outsourced" radio button
    private static int partCounter = 1005; // This is the variable that determines part ID values. This value is set to be one (1) greater than the amount of parts included in the testdata (e.g., within the loadTestData() function of the Application Class).

    /**
     * This function is called to change the scene of the JavaFX application to the scene corresponding with the Main form.
     * This function is used if a part is successfully added to the parts list or if the user clicks the "Cancel" button in the "Add Part" pane.
     * @throws IOException
     * */
    @FXML
    protected void switch_BackToMain() throws IOException {
        Scene scene;
        Parent root;
        FXMLLoader addPartView = new FXMLLoader(getClass().getResource("/group/ims/mainView.fxml"));
        root = addPartView.load();
        scene = new Scene(root, 1076, 520);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function exists to appropriately switch the label text within the last field of the "Add Part" pane; the text toggles between "Machine ID" and "Company Name".
     * This function is called when the user toggles between both the "In House" and "Outsourced" radio buttons in the "Add Part" pane.
     * */
    public void buttonHandler() {
        if (inHouseButton.isSelected()) {
            MachineID_CompanyName.setText("Machine ID");
        } else {
            MachineID_CompanyName.setText("Company Name");
        }
    }
    /**
     * This is the first function called when the "Add Part" pane is loaded and sets the partID within the appropriate textfield  of the new part.
     * */
    public void initialize() {
        partID.setText(String.valueOf(partCounter));
    }

    /**
     * This function is called to alert the user that a string value was entered in a textfield where an integer or double input was expected.
     * */
    private void numFormatError() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input has been provided. Make sure that strings are not entered where numbers are expected.");
        alert.show();
    }

    /**
     * This function creates a new part within the parts list based off the data input into the various textfields of the "Add Part" pane. This function provides input validation
     * to ensure that (1) strings are not entered where an integer or double input is expected, (2) the "min" value is less than the "max" value, and (3) the "inv" value is between
     * the "min" and "max" values.
     * @throws IOException
     */
    @FXML
    public void savePart() throws IOException {
        /*The try/catch block is in place to handle if the user enters string data into a textfield that is expected an integer or double input value.
        So, if the user does in fact enter string data into a textfield that is expecting an integer or double, a NumberFormatException will be thrown, which is addressed
        in the catch block.*/
        try {
            int ID = Integer.valueOf(partID.getText()); // sets the value of part's ID to whatever is entered into the partID textfield.
            String name = partName.getText(); // sets the value of the part's name to whatever is entered into the partName textfield.
            double price = Double.valueOf(part_Price.getText()); // sets the value of the part's price to whatever is entered into the part_Price textfield.
            int inventory = Integer.valueOf(part_InventoryLevel.getText()); // sets the value of the part's stock to whatever is entered into the part_InventoryLevel textfield.
            int min = Integer.valueOf(partMin.getText()); // sets the value of the part's min to whatever is entered into the partMin textfield.
            int max = Integer.valueOf(partMax.getText()); // sets the value of the part to whatever is entered into the partMax textfield.

            /*The outer if/else block below determines if the input value for the partMin textfield is less than the input value for the partMax textfield.
            * Additionally, the outer if/else block determines if the input value for the part_InventoryLevel textfield is a value between the input values for the partMin
            * and partMax textfields.
            *
            * The inner if/else block assumes that values for part data were entered appropriately. This if/else block evaluates whether the part is an inhouse object or
            * an outsourced object. */

            if ((Integer.valueOf(partMax.getText()) > Integer.valueOf(partMin.getText())) && (Integer.valueOf(part_InventoryLevel.getText()) >= Integer.valueOf(partMin.getText()) && Integer.valueOf(part_InventoryLevel.getText()) <= Integer.valueOf(partMax.getText()))) {
                if (inHouseButton.isSelected()) {
                    int machineID = Integer.valueOf(toggleField.getText());
                    Inhouse newPart = new Inhouse(ID, name, price, inventory, min, max, machineID);
                    getAllParts().add(newPart);

                } else {
                    String companyName = toggleField.getText();
                    Outsourced newPart = new Outsourced(ID, name, price, inventory, min, max, companyName);
                    getAllParts().add(newPart);
                }
                partCounter++; // increment the partCounter variable
                switch_BackToMain();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The inventory input must be a value between (inclusive) the max and min input values. Furthermore, the min input value must be less than the max input value.");
                alert.show();
            }

        } catch (NumberFormatException e) {
            numFormatError();
        }
    }

} // last bracket

