package lk.acpt.demofx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.acpt.demofx.dto.PhoneDto;
import lk.acpt.demofx.model.PhoneModel;
import lk.acpt.demofx.tm.PhoneTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    private TableView<PhoneTM> tblPhone;

    @FXML
    void load(ActionEvent event) {
        //table setup
        tblPhone.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        tblPhone.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblPhone.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblPhone.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPhone.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));

        //get data from model class method and get returned arraylist of DTO
        ArrayList<PhoneDto> allPhone = PhoneModel.getAllPhone();

        //convert dto arraylist to a TM arraylist
        ArrayList<PhoneTM> phoneTMS = new ArrayList<>();

        for (PhoneDto dto : allPhone) {
            phoneTMS.add(new PhoneTM(dto.getPid(), dto.getBrand(), dto.getModel(), dto.getPrice(), dto.getQty()));
        }

        tblPhone.setItems(FXCollections.observableArrayList(phoneTMS));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //table setup
        tblPhone.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("pid"));
        tblPhone.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblPhone.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblPhone.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblPhone.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));

        //get data from model class method and get returned arraylist of DTO
        ArrayList<PhoneDto> allPhone = PhoneModel.getAllPhone();

        //convert dto arraylist to a TM arraylist
        ArrayList<PhoneTM> phoneTMS = new ArrayList<>();

        for (PhoneDto dto : allPhone) {
            phoneTMS.add(new PhoneTM(dto.getPid(), dto.getBrand(), dto.getModel(), dto.getPrice(), dto.getQty()));
        }

        tblPhone.setItems(FXCollections.observableArrayList(phoneTMS));
    }
}
