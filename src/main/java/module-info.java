module group.ims {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.ims.Controllers to javafx.fxml;
    opens group.ims.Models to javafx.base;
    exports group.ims;
}