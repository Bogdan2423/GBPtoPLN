package project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.math3.util.Precision;

public class GUI extends Application {
    private double sceneWidth=800;
    private double sceneHeight=1000;

    public void start(Stage stage) {
        TextField gbpPrice = new TextField();
        gbpPrice.setFont(Font.font("Verdana", 20));
        Label gbpLabel = new Label("Price in GBP:");
        gbpLabel.setFont(Font.font("Verdana", 20));

        TextField plnPrice = new TextField();
        plnPrice.setFont(Font.font("Verdana", 20));
        Label plnLabel = new Label("Price in PLN:");
        plnLabel.setFont(Font.font("Verdana", 20));

        Button submitButton = new Button("Submit");

        Label error = new Label();
        error.setFont(Font.font("Verdana", 20));
        Label rate = new Label();
        rate.setFont(Font.font("Verdana", 20));

        VBox mainBox = new VBox(gbpLabel, gbpPrice, plnLabel, plnPrice, submitButton, rate, error);
        mainBox.setAlignment(Pos.BASELINE_CENTER);
        mainBox.setPadding(new Insets(100));
        mainBox.setSpacing(50);

        Scene scene = new Scene(mainBox,sceneWidth,sceneHeight);
        stage.setScene(scene);

        stage.show();

        submitButton.setOnAction((event0 -> {
            boolean isGbp = true;
            String priceString;
            double price = 0;
            Double[] result = {0.0, 0.0};
            if (!gbpPrice.getText().isEmpty()) {
                priceString = gbpPrice.getText();
                isGbp = true;
            } else if (!plnPrice.getText().isEmpty()) {
                priceString = plnPrice.getText();
                isGbp = false;
            } else {
                priceString = "0";
            }

            try {
                price = Double.parseDouble(priceString);
            } catch (Exception ex) {
                error.setText("Error: " + ex);
            }

            try {
                result = CurrencyConverter.convertGBP_PLN(price, isGbp);
            } catch (Exception ex) {
                error.setText("Error: " + ex);
            }
            if (isGbp)
                plnPrice.setText(String.valueOf(Precision.round(result[1],2)));
            else
                gbpPrice.setText(String.valueOf(Precision.round(result[1],2)));
            rate.setText("1 GBP = "+Precision.round(result[0],2)+" PLN");
        }));
    }
}
