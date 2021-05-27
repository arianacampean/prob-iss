package controller;

import Service.Service;
import domain.Comanda;
import domain.Medicament;
import domain.Stare;
import domain.Utilizator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FarmacistController {
    ObservableList<Medicament> model2= FXCollections.observableArrayList();
    ObservableList<Comanda> model= FXCollections.observableArrayList();
    private Service serv;
    private Utilizator user;
    @FXML
    ListView<Comanda>comm;
    @FXML
    ListView<Medicament>medd;
    @FXML
    TableColumn<Comanda,String>id_med1;
    @FXML
    TableColumn<Comanda,String>cant;
    @FXML
    TableColumn<Comanda,String>staree;
    @FXML
    TableColumn<Comanda,String>sec;
    @FXML
    TableColumn<Comanda,String>nr_com;
    @FXML
    TableColumn<Medicament,String>id_med2;
    @FXML
    TableColumn<Medicament,String>num;
    @FXML
    TableView<Comanda>comView;
    @FXML
    TableView<Medicament>medView;
    @FXML
    TableColumn<Medicament,String>cantt;
    @FXML
    Button ono;
    @FXML
    Button nefin;
    Stage stage;


    public void setService(Service serv,Stage stage)
    {
        this.serv=serv;
        this.stage=stage;
        lista();
        lista2();
    }
    public void setUtil(Utilizator u)
    {
        this.user=u;
    }

    @FXML
    public void initialize()
    {
        id_med1.setCellValueFactory(new PropertyValueFactory<Comanda, String>("id_medicament"));
        cant.setCellValueFactory(new PropertyValueFactory<Comanda, String>("cantitate"));
        nr_com.setCellValueFactory(new PropertyValueFactory<Comanda, String>("nr_comanda"));
        staree.setCellValueFactory(new PropertyValueFactory<Comanda, String>("stare"));
        sec.setCellValueFactory(new PropertyValueFactory<Comanda, String>("sectie"));
        id_med2.setCellValueFactory(new PropertyValueFactory<Medicament, String>("id"));
        num.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nume"));
        cantt.setCellValueFactory(new PropertyValueFactory<Medicament, String>("cantitate_pe_stoc"));
        comView.setItems(model);
        medView.setItems(model2);
    }
    public void lista()
    {

        List<Comanda>com= (List<Comanda>) serv.get_allCom();
        model.setAll(com);


    }
    public void lista2()
    {
        List<Medicament> med= (List<Medicament>) serv.get_allMed();
        model2.setAll(med);
    }
    public void onoreaza()
    {
        Comanda selected=comView.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Cantitatea este prea mare/Nu este pe stoc");
        int cant=selected.getCantitate();
        int id_med=selected.getId_medicament();

        List<Medicament> meds= (List<Medicament>) serv.get_allMed();
        Medicament mss=new Medicament();
        for (Medicament m :meds)
        {
            if(m.getId()==id_med)
            {
                mss=m;
            }

        }
        int cant2=mss.getCantitate_pe_stoc();
        if(cant2-cant<0)
        {
            a.show();
            selected.setStare(Stare.NU_ESTE_PE_STOC);
            serv.update_com(selected);
            lista();
        }
        else {
            int new_cant=cant2-cant;
            mss.setCantitate_pe_stoc(new_cant);
            serv.update_med(mss);
            serv.detele_com(selected);
            lista();
            lista2();
        }

    }
    public void nefinalizat()
    {
        Comanda selected=comView.getSelectionModel().getSelectedItem();
        selected.setStare(Stare.NU_ESTE_PE_STOC);
        serv.update_com(selected);
        lista();
    }
    @FXML
    public void SignOut()
    {
        stage.close();
    }
}
