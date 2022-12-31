module com.monopoly.monopolyprojetoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.monopoly.monopolyprojetoo to javafx.fxml;
    exports com.monopoly.monopolyprojetoo;
}