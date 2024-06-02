package lk.acpt.demofx.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.acpt.demofx.dto.OrderDetailDto;
import lk.acpt.demofx.dto.PhoneDto;
import lk.acpt.demofx.model.PhoneModel;
import lk.acpt.demofx.tm.OrderDetailTM;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private Button placeOrder;

    @FXML
    private TableView<OrderDetailTM> tblDevices;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtId;

    @FXML
    private Label lblSubTotal;

    @FXML
    private TextField txtOrderId;

    private ArrayList<OrderDetailTM> orderDetailTMS;

    private double subTotal =0.0;

    @FXML
    void addToCart(ActionEvent event) {
        double total = Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQty.getText());

        OrderDetailTM orderDetailTM = new OrderDetailTM(Integer.parseInt(txtId.getText()), txtBrand.getText(),
                txtModel.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText()),
                total);

        orderDetailTMS.add(orderDetailTM);

        tblDevices.setItems(FXCollections.observableArrayList(orderDetailTMS));

        //calculating and displaying subtotal
        subTotal+=total;
        lblSubTotal.setText(String.valueOf(subTotal));
    }

    @FXML
    void placeOrder(ActionEvent event) {
        //get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String orderDate = dateFormat.format(date);

        //convert TM list to DTO list
        ArrayList<OrderDetailDto> list = new ArrayList<>();
        for(OrderDetailTM tm : orderDetailTMS){
            list.add(new OrderDetailDto(tm.getId(),tm.getBrand(),tm.getModel(),tm.getPrice(),tm.getQty(),tm.getTotal()));
        }

        try {
            PhoneModel.makeOrder(txtOrderId.getText(),subTotal,orderDate,list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void searchById(ActionEvent event) {
        String pid = txtId.getText();

        PhoneDto phoneDto = PhoneModel.searchPhone(pid);

        txtBrand.setText(phoneDto.getBrand());
        txtModel.setText(phoneDto.getModel());
        txtPrice.setText(String.valueOf(phoneDto.getPrice()));
        txtQtyOnHand.setText(String.valueOf(phoneDto.getQty()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //table initialization
        tblDevices.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblDevices.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblDevices.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblDevices.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblDevices.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblDevices.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));

        orderDetailTMS = new ArrayList<>();
    }
}
