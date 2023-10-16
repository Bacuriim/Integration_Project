package com.br.eletra.controller;

import com.br.eletra.dto.CategoryDTO;
import com.br.eletra.dto.LineDTO;
import com.br.eletra.dto.ModelDTO;
import com.br.eletra.service.MeterCategoryService;
import com.br.eletra.service.MeterLineService;
import com.br.eletra.service.MeterModelService;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControllerMainTest extends ApplicationTest {

    private ControllerMain controller;

    @Before
    public void setUp() {
        controller = Mockito.spy(ControllerMain.class);
        controller.tpLine = new TitledPane();
        controller.cbbLine = new ComboBox<>();
        controller.tpModel = new TitledPane();
        controller.treeView = new TreeView<>();
        controller.accordion = new Accordion();
        controller.meterLineService = Mockito.mock(MeterLineService.class);
        controller.meterCategoryService = Mockito.mock(MeterCategoryService.class);
        controller.meterModelService = Mockito.mock(MeterModelService.class);
    }

    @Test
    public void initializeControllerTest() {
        // Given
        Mockito.doNothing().when(controller).comboBoxSelect();
        controller.tpLine.setDisable(true);

        // When
        controller.initialize(null , null);

        // Then
        assertEquals(controller.tpLine , controller.accordion.getExpandedPane());
        assertTrue(controller.tpModel.isDisable());
        verify(controller).comboBoxSelect();
    }

    @Test
    public void comboBoxSelectTest01() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        Mockito.when(controller.meterLineService.getAllMeterLines()).thenReturn(mockList);

        // When
        controller.comboBoxSelect();

        // Then
        assertEquals(FXCollections.observableArrayList(mockList), controller.cbbLine.getItems());
        verify(controller.meterLineService).getAllMeterLines();
    }

    /**
     * O que está sendo testado
     */
    @Test
    public void comboBoxSelectTest02() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        Mockito.when(controller.meterLineService.getAllMeterLines()).thenReturn(mockList);

        // When
        controller.comboBoxSelect();

        // Then
        assertEquals(FXCollections.observableArrayList(mockList), controller.cbbLine.getItems());
        verify(controller.meterLineService).getAllMeterLines();
    }

    @Test
    public void comboBoxSelectTest03() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        mockList.add(new LineDTO("Ares" , (short) 1));
        mockList.add(new LineDTO("Cronos" , (short) 2));
        Mockito.when(controller.meterLineService.getAllMeterLines()).thenReturn(mockList);
        controller.comboBoxSelect();

        // When
        controller.cbbLine.setValue(controller.cbbLine.getItems().get(0));

        // Then
        verify(controller).openTreeView(controller.cbbLine.getItems().get(0));
    }

    @Test
    public void comboBoxSelectTest04() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        mockList.add(new LineDTO("Ares" , (short) 1));
        mockList.add(new LineDTO("Cronos" , (short) 2));
        Mockito.when(controller.meterLineService.getAllMeterLines()).thenReturn(mockList);
        controller.comboBoxSelect();

        // When
        controller.cbbLine.setValue(controller.cbbLine.getItems().get(1));

        // Then
        verify(controller).openTreeView(controller.cbbLine.getItems().get(1));
    }

    @Test
    public void comboBoxSelectTest05() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        Mockito.when(controller.meterLineService.getAllMeterLines()).thenReturn(mockList);

        // When
        controller.comboBoxSelect();

        // Then
        assertTrue(controller.cbbLine.getItems().isEmpty());
    }

    @Test
    public void openTreeViewTest01() {
        // Given
        LineDTO mockLine = new LineDTO("Ares" , (short) 1);
        CategoryDTO mockCategory = new CategoryDTO("Ares TB" , (short) 1);
        List<CategoryDTO> mockCategoryList = new ArrayList<>();
        List<ModelDTO> mockModelList = new ArrayList<>();
        TreeItem mockTreeView = new TreeItem<>(mockLine);
        controller.openTreeView(mockLine);

        // When
        controller.tpLine.setExpanded(false);
        controller.tpModel.setDisable(false);
        controller.tpModel.setExpanded(true);
        when(controller.meterCategoryService.getAllMeterCategories(mockLine)).thenReturn(mockCategoryList);
        when(controller.meterModelService.getAllMeterModels(mockCategory)).thenReturn(mockModelList);
        mockTreeView.setExpanded(true);

        // Then
        assertFalse(controller.tpLine.isExpanded());
        assertFalse(controller.tpModel.isDisable());
        assertTrue(controller.tpModel.isExpanded());
        assertTrue(controller.treeView.isVisible());
        assertTrue(mockTreeView.isExpanded());
        assertEquals(controller.treeView.getExpandedItemCount() , 1);
        verify(controller).openTreeView(mockLine);
    }

    @Test
    public void openTreeViewTest02() {
        // Given
        LineDTO mockLine = new LineDTO("Cronos" , (short) 2);
        CategoryDTO mockCategory = new CategoryDTO("Cronos Old" , (short) 3);
        List<CategoryDTO> mockCategoryList = new ArrayList<>();
        List<ModelDTO> mockModelList = new ArrayList<>();
        TreeItem mockTreeView = new TreeItem<>(mockLine);
        controller.openTreeView(mockLine);

        // When
        controller.tpLine.setExpanded(true);
        controller.tpModel.setDisable(true);
        controller.tpModel.setExpanded(true);
        when(controller.meterCategoryService.getAllMeterCategories(mockLine)).thenReturn(mockCategoryList);
        when(controller.meterModelService.getAllMeterModels(mockCategory)).thenReturn(mockModelList);
        mockTreeView.setExpanded(true);

        // Then
        assertTrue(controller.tpLine.isExpanded());
        assertTrue(controller.tpModel.isDisable());
        assertTrue(controller.tpModel.isExpanded());
        assertTrue(controller.treeView.isVisible());
        assertTrue(mockTreeView.isExpanded());
        assertEquals(controller.treeView.getExpandedItemCount() , 1);
        verify(controller).openTreeView(mockLine);
    }

    @Test
    public void openTreeViewTest03() {
        // Given
        LineDTO mockLine = new LineDTO("Cronos" , (short) 2);
        CategoryDTO mockCategory = new CategoryDTO("Cronos Old" , (short) 3);
        List<CategoryDTO> mockCategoryList = new ArrayList<>();
        List<ModelDTO> mockModelList = new ArrayList<>();
        TreeItem mockTreeView = new TreeItem<>(mockLine);
        controller.openTreeView(mockLine);

        // When
        controller.tpLine.setExpanded(false);
        controller.tpModel.setDisable(false);
        controller.tpModel.setExpanded(true);
        when(controller.meterCategoryService.getAllMeterCategories(mockLine)).thenReturn(mockCategoryList);
        when(controller.meterModelService.getAllMeterModels(mockCategory)).thenReturn(mockModelList);
        mockTreeView.setExpanded(true);

        // Then
        assertFalse(controller.tpLine.isExpanded());
        assertFalse(controller.tpModel.isDisable());
        assertTrue(controller.tpModel.isExpanded());
        assertTrue(controller.treeView.isVisible());
        assertTrue(mockTreeView.isExpanded());
        assertEquals(controller.treeView.getExpandedItemCount() , 1);
        verify(controller).openTreeView(mockLine);
    }
}
