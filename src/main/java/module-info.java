module com.example.matcompmppfinalcomponentes {
    requires javafx.controls;
    requires javafx.fxml;
    //requires org.apache.logging.log4j;
    requires java.rmi;
    requires com.google.gson;


    opens com.example.matcompmppfinalcomponentes to javafx.fxml;
    exports com.example.matcompmppfinalcomponentes;
    exports es.uah.matcomp.mp.pfinal.componentesylogs;
    opens es.uah.matcomp.mp.pfinal.componentesylogs to javafx.fxml;
}