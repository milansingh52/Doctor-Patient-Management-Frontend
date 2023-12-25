module com.ms.dpms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.json; 
    requires java.net.http;
    
    opens com.ms.dpms to javafx.fxml;
    exports com.ms.dpms;
}
