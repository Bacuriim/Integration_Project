package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.CategorysEnum;
import model.MeterLinesEnum;
import model.MeterModelsEnum;

public class ControllerMain implements Initializable {

    @FXML
    private TitledPane tpLine;

    @FXML
    private ComboBox<MeterLinesEnum> cbbLine;

    @FXML
    private TitledPane tpModel;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Accordion accordion;

    @Override

    public void initialize(URL location,ResourceBundle resources) {
        accordion.setExpandedPane(tpLine);
        tpModel.setDisable(true);
        cbbLine.setItems(FXCollections.observableArrayList(MeterLinesEnum.values()));
        cbbLine.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView()));
    }

    private void openTreeView() {
        tpModel.setDisable(false);
        tpModel.setExpanded(true);

        switch(cbbLine.getValue().toString()){

            case "Ares":
                TreeItem<String> rootFirst = new TreeItem<>(MeterLinesEnum.ARES.toString());
                treeView.setRoot(rootFirst);
                TreeItem<String> rootTB = new TreeItem<>(CategorysEnum.ARES_TB.toString());
                TreeItem<String> rootTHS = new TreeItem<>(CategorysEnum.ARES_THS.toString());

                rootFirst.getChildren().addAll(rootTB,rootTHS);

                TreeItem<String> modelAres1 = new TreeItem<>(MeterModelsEnum.ARES_8023_15.toString());
                TreeItem<String> modelAres2 = new TreeItem<>(MeterModelsEnum.ARES_8023_200.toString());
                TreeItem<String> modelAres3 = new TreeItem<>(MeterModelsEnum.ARES_8023_25.toString());

                TreeItem<String> modelAres4 = new TreeItem<>(MeterModelsEnum.ARES_7021.toString());
                TreeItem<String> modelAres5 = new TreeItem<>(MeterModelsEnum.ARES_7031.toString());
                TreeItem<String> modelAres6 = new TreeItem<>(MeterModelsEnum.ARES_7023.toString());

                rootTHS.getChildren().add(modelAres1);
                rootTHS.getChildren().add(modelAres2);
                rootTHS.getChildren().add(modelAres3);

                rootTB.getChildren().add(modelAres4);
                rootTB.getChildren().add(modelAres5);
                rootTB.getChildren().add(modelAres6);
                break;

            case "Cronos":
                TreeItem<String> rootSecond = new TreeItem<>(MeterLinesEnum.CRONOS.toString());
                treeView.setRoot(rootSecond);
                TreeItem<String> rootCO = new TreeItem<>(CategorysEnum.CRONOS_OLD.toString());
                TreeItem<String> rootCl = new TreeItem<>(CategorysEnum.CRONOS_L.toString());
                TreeItem<String> rootCNG = new TreeItem<>(CategorysEnum.CRONOS_NG.toString());

                rootSecond.getChildren().addAll(rootCO , rootCl , rootCNG);

                TreeItem<String> modelCronos1 = new TreeItem<>(MeterModelsEnum.CRONOS_6001A.toString());
                TreeItem<String> modelCronos2 = new TreeItem<>(MeterModelsEnum.CRONOS_6003.toString());
                TreeItem<String> modelCronos3 = new TreeItem<>(MeterModelsEnum.CRONOS_7023.toString());

                TreeItem<String> modelCronos4 = new TreeItem<>(MeterModelsEnum.CRONOS_6021L.toString());
                TreeItem<String> modelCronos5 = new TreeItem<>(MeterModelsEnum.CRONOS_7023L.toString());

                TreeItem<String> modelCronos6 = new TreeItem<>(MeterModelsEnum.CRONOS_6001NG.toString());
                TreeItem<String> modelCronos7 = new TreeItem<>(MeterModelsEnum.CRONOS_6003NG.toString());
                TreeItem<String> modelCronos8 = new TreeItem<>(MeterModelsEnum.CRONOS_6021NG.toString());
                TreeItem<String> modelCronos9 = new TreeItem<>(MeterModelsEnum.CRONOS_6031NG.toString());
                TreeItem<String> modelCronos10 = new TreeItem<>(MeterModelsEnum.CRONOS_7021NG.toString());
                TreeItem<String> modelCronos11 = new TreeItem<>(MeterModelsEnum.CRONOS_7023NG.toString());

                rootCO.getChildren().add(modelCronos1);
                rootCO.getChildren().add(modelCronos2);
                rootCO.getChildren().add(modelCronos3);

                rootCl.getChildren().add(modelCronos4);
                rootCl.getChildren().add(modelCronos5);

                rootCNG.getChildren().add(modelCronos6);
                rootCNG.getChildren().add(modelCronos7);
                rootCNG.getChildren().add(modelCronos8);
                rootCNG.getChildren().add(modelCronos9);
                rootCNG.getChildren().add(modelCronos10);
                rootCNG.getChildren().add(modelCronos11);
                break;
        }
    }
}
