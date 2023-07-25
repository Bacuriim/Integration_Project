package controller;

import connection.HibernateConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CategoryEntity;
import model.LineEntity;
import model.ModelEntity;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {

    @FXML
    private TitledPane tpLine;

    @FXML
    private ComboBox<LineEntity> cbbLine;

    @FXML
    private TitledPane tpModel;

    @FXML
    private TreeView<LineEntity> treeView;

    @FXML
    private Accordion accordion;

    private final Session session = HibernateConnection.buildSessionFactory().openSession();

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);

        comboBoxSelect();
    }

    private void comboBoxSelect(){
        List<LineEntity> lineList = session.createQuery("FROM LineEntity").list();

        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }

    private void openTreeView(LineEntity selectedLine) {
        tpLine.setExpanded(false);
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        List<CategoryEntity> categoryEntityList = session.createQuery(String.format("FROM CategoryEntity WHERE id_line = '%s'",selectedLine)).list();
        TreeItem setTreeView = new TreeItem<>(selectedLine);
        setTreeView.setExpanded(true);

       categoryEntityList.forEach((category) -> {
            TreeItem<CategoryEntity> categoryItem = new TreeItem<>(category);
            setTreeView.getChildren().add(categoryItem);

            List<ModelEntity> modelEntityList = session.createQuery(String.format("FROM ModelEntity WHERE id_category = '%s'",category)).list();
            modelEntityList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
       });
       treeView.setRoot(setTreeView);
    }
}
