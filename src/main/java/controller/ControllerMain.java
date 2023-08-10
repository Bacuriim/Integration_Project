package controller;

import configuration.HibernateConfiguration;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.MeterCategoryEntity;
import model.MeterLineEntity;
import model.MeterModelEntity;
import org.hibernate.Session;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    private final Session session = HibernateConfiguration.buildSessionFactory().openSession();

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
        comboBoxSelect();
    }

    private void comboBoxSelect() {
        List<MeterLineEntity> lineList = session.createQuery("FROM MeterLineEntity").list();
        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }

    private void openTreeView(MeterLineEntity selectedLine) {
        tpLine.setExpanded(false);
        tpModel.setDisable(false);
        tpModel.setExpanded(true);
        List<MeterCategoryEntity> meterCategoryEntityList = session.createQuery(String.format("FROM MeterCategoryEntity WHERE line_id = '%s'",selectedLine)).list();
        TreeItem showTreeView = new TreeItem<>(selectedLine);
        showTreeView.setExpanded(true);
        meterCategoryEntityList.forEach((category) -> {
            TreeItem<MeterCategoryEntity> categoryItem = new TreeItem<>(category);
            showTreeView.getChildren().add(categoryItem);
            List<MeterModelEntity> meterModelEntityList = session.createQuery(String.format("FROM MeterModelEntity WHERE category_id = '%s'",category)).list();
            meterModelEntityList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
        });
        treeView.setRoot(showTreeView);
    }
}
