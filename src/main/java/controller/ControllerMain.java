package controller;

import com.br.eletra.models.MeterCategoryEntity;
import com.br.eletra.models.MeterLineEntity;
import com.br.eletra.models.MeterModelEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import service.MeterCategoryService;
import service.MeterLineService;
import service.MeterModelService;

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

    MeterLineService meterLineService = new MeterLineService();
    MeterCategoryService meterCategoryService = new MeterCategoryService();
    MeterModelService meterModelService = new MeterModelService();

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
        comboBoxSelect();
    }

    private void comboBoxSelect() {
        List<MeterLineEntity> lineList = meterLineService.getAllMeterLines();
        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }

    private void openTreeView(MeterLineEntity selectedLine) {
        tpLine.setExpanded(false);
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        List<MeterCategoryEntity> categoryList = meterCategoryService.getAllMeterCategories(selectedLine);
        TreeItem showTreeView = new TreeItem<>(selectedLine);
        showTreeView.setExpanded(true);

        categoryList.forEach((category) -> {
            TreeItem<MeterCategoryEntity> categoryItem = new TreeItem<>(category);
            showTreeView.getChildren().add(categoryItem);

            List<MeterModelEntity> modelList = meterModelService.getAllMeterModels(category);
            modelList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
        });
        treeView.setRoot(showTreeView);
    }
}
