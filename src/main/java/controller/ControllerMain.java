package controller;

import com.br.eletra.configuration.HibernateConfiguration;
import com.br.eletra.DAO.MeterCategoryDAO;
import com.br.eletra.DAO.MeterLineDAO;
import com.br.eletra.DAO.MeterModelDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.br.eletra.model.MeterCategoryEntity;
import com.br.eletra.model.MeterLineEntity;
import com.br.eletra.model.MeterModelEntity;
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
    private final MeterLineDAO meterLineDAO = new MeterLineDAO(session);
    private final MeterCategoryDAO meterCategoryDAO = new MeterCategoryDAO(session);
    private final MeterModelDAO meterModelDAO = new MeterModelDAO(session);

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
        comboBoxSelect();
    }

    private void comboBoxSelect() {
        List<MeterLineEntity> lineList = meterLineDAO.getAllMeterLines();
        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }

    private void openTreeView(MeterLineEntity selectedLine) {
        tpLine.setExpanded(false);
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        List<MeterCategoryEntity> categoryList = meterCategoryDAO.getCategoriesForLine(selectedLine);
        TreeItem showTreeView = new TreeItem<>(selectedLine);
        showTreeView.setExpanded(true);

        categoryList.forEach((category) -> {
            TreeItem<MeterCategoryEntity> categoryItem = new TreeItem<>(category);
            showTreeView.getChildren().add(categoryItem);

            List<MeterModelEntity> meterModelEntityList = meterModelDAO.getModelsForCategory(category);

            meterModelEntityList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
        });
        treeView.setRoot(showTreeView);
    }
}
