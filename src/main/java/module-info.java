module br.com.trem.ex_trem_visual {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.trem.ex_trem_visual to javafx.fxml;
    exports br.com.trem.ex_trem_visual;
}