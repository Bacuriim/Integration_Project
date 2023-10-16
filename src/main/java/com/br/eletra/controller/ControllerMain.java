package com.br.eletra.controller;

import com.br.eletra.dto.CategoryDTO;
import com.br.eletra.dto.LineDTO;
import com.br.eletra.dto.ModelDTO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.br.eletra.service.MeterCategoryService;
import com.br.eletra.service.MeterLineService;
import com.br.eletra.service.MeterModelService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {

    @FXML
    protected TitledPane tpLine;

    @FXML
    protected ComboBox<LineDTO> cbbLine;

    @FXML
    protected TitledPane tpModel;

    @FXML
    protected TreeView<LineDTO> treeView;

    @FXML
    protected Accordion accordion;

    protected MeterLineService meterLineService = new MeterLineService();
    protected MeterCategoryService meterCategoryService = new MeterCategoryService();
    protected MeterModelService meterModelService = new MeterModelService();

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
        comboBoxSelect();
    }

    public void comboBoxSelect() {
        List<LineDTO> lineList = meterLineService.getAllMeterLines();
        cbbLine.setItems(FXCollections.observableArrayList(lineList));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }

    protected void openTreeView(LineDTO selectedLine) {
        tpLine.setExpanded(false);
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        List<CategoryDTO> categoryList = meterCategoryService.getAllMeterCategories(selectedLine);
        TreeItem showTreeView = new TreeItem<>(selectedLine);
        showTreeView.setExpanded(true);

        categoryList.forEach((category) -> {
            TreeItem<CategoryDTO> categoryItem = new TreeItem<>(category);
            showTreeView.getChildren().add(categoryItem);

            List<ModelDTO> modelList = meterModelService.getAllMeterModels(category);
            modelList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
        });
        treeView.setRoot(showTreeView);
    }
}
