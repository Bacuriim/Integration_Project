package com.br.eletra.controller;

import com.br.eletra.dto.LineDTO;
import com.br.eletra.service.MeterCategoryService;
import com.br.eletra.service.MeterLineService;
import com.br.eletra.service.MeterModelService;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ControllerMainTest extends ApplicationTest {

		@InjectMocks
		private ControllerMain controller;

		@Mock
		private TitledPane tpLine;

		@Mock
		private ComboBox<LineDTO> cbbLine;

		@Mock
		private TitledPane tpModel;

		@Mock
		private TreeView<LineDTO> treeView;

		@Mock
		private Accordion accordion;

		@Before
		public void setUp() {
				controller = spy(ControllerMain.class);
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
		public void testInitialize() {
				controller.initialize(null , null);
				when(controller.accordion.getExpandedPane()).thenReturn(tpLine);



				assertTrue(tpModel.isDisable());
				verifyNoMoreInteractions(controller);
		}
}