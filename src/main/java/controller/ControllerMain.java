package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import model.MeterCategoryEntity;
import model.MeterModelEntity;
import connection.HibernateConnection;
import model.MeterLineEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.Session;

public class ControllerMain implements Initializable {

    @FXML
    private TitledPane tpLine;

    @FXML
    private ComboBox<MeterLineEntity> cbbLine;

    @FXML
    private TitledPane tpModel;

    @FXML
    private TreeView<MeterLineEntity> treeView;

    @FXML
    private Accordion accordion;

    public final Session session = HibernateConnection.buildSessionFactory().openSession();

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
        openComboBox();
    }
    private void openComboBox(){
        List<MeterLineEntity> lineList = session.createQuery("FROM MeterLineEntity").list();
        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }
    private void openTreeView(MeterLineEntity selectedLine) {
        tpLine.setExpanded(false);
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        List<MeterCategoryEntity> categoryList = session.createQuery(String.format("FROM MeterCategoryEntity WHERE line_id = '%s'",selectedLine)).list();
        TreeItem showTreeView = new TreeItem<>(selectedLine);
        showTreeView.setExpanded(true);

        categoryList.forEach((category) -> {
            TreeItem<MeterCategoryEntity> categoryItem = new TreeItem<>(category);
            showTreeView.getChildren().add(categoryItem);

            List<MeterModelEntity> modelList = session.createQuery(String.format("FROM MeterModelEntity WHERE category_id = '%s'",category)).list();
            modelList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
        });
        treeView.setRoot(showTreeView);
    }
}
