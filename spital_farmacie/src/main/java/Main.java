import Service.Service;
import controller.FarmacistController;
import controller.LoginController;
import domain.Comanda;
import domain.Medicament;
import domain.Stare;
import domain.Utilizator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repo.ComandaRepo;
import repo.MedicamentRepo;
import repo.UtilizatorRepo;
import repo.interfete.RepoComanda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static SessionFactory sessionFactory;

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            initialize();
            ComandaRepo repo_com = new ComandaRepo(sessionFactory);
            MedicamentRepo repo_med = new MedicamentRepo(sessionFactory);
            UtilizatorRepo repo_util = new UtilizatorRepo(sessionFactory);
           // Comanda c=new Comanda(3,4,2,Stare.NU_ESTE_PE_STOC,8);
           // repo_com.add(c);
          //  System.out.println(repo_util.findAll());

           // repo_com.add();
//            Comanda c=new Comanda(10,4,6,Stare.NU_ESTE_PE_STOC,4);
//            c.setId(9);
//            repo_com.update(c);
            Service serv = new Service(repo_com, repo_med, repo_util);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("views/autentificare.fxml"));
                AnchorPane root = (AnchorPane) loader.load();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Autentificare");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(root, 600, 450);
                dialogStage.setScene(scene);
                LoginController utilCont = loader.getController();
                utilCont.setService(serv,dialogStage);
                dialogStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Exception " + e);
            e.printStackTrace();

        }
    }
}
