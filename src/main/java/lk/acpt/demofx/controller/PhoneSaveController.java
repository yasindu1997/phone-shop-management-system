package lk.acpt.demofx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.acpt.demofx.dto.PhoneDto;
import lk.acpt.demofx.model.PhoneModel;

import java.sql.SQLException;

public class PhoneSaveController {
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void cancle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void save(ActionEvent event) {

        //collecting data
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        //save data to the database
        PhoneModel.savePhone(new PhoneDto(brand, model, price, qty));

    }

    public void cleanInputs(){
        txtId.clear();
        txtBrand.clear();
        txtModel.clear();
        txtPrice.clear();
        txtQty.clear();
    }
}
