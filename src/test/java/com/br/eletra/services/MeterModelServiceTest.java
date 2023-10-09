package com.br.eletra.services;

import com.br.eletra.dto.CategoryDTO;
import com.br.eletra.dto.LineDTO;
import com.br.eletra.dto.ModelDTO;
import com.br.eletra.service.MeterModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeterModelServiceTest {

    private MeterModelService service;
    private Client client;
    private WebTarget webTarget;
    private Invocation.Builder builder;
    private Response response;

    @BeforeEach
    public void setUp() {
        client = mock(Client.class);
        webTarget = mock(WebTarget.class);
        builder = mock(Invocation.Builder.class);
        response = mock(Response.class);

        service = new MeterModelService();
        service.setClient(client);

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
    }

    @Test
    public void getAllMeterModelsAndConvertToString() {
        String jsonResponse = "[\n" + "  {\n" + "    \"id\": 1,\n" + "    \"modelName\": \"Cronos 6001-A\",\n" + "    \"category\": \"Cronos Old\"\n" + "  },\n" + "  {\n" + "    \"id\": 2,\n" + "    \"modelName\": \"Cronos 6003\",\n" + "    \"category\": \"Cronos Old\"\n" + "  },\n" + "  {\n" + "    \"id\": 3,\n" + "    \"modelName\": \"Cronos 7023\",\n" + "    \"category\": \"Cronos Old\"\n" + "  },\n" + "  {\n" + "    \"id\": 4,\n" + "    \"modelName\": \"Cronos 6021L\",\n" + "    \"category\": \"Cronos L\"\n" + "  },\n" + "  {\n" + "    \"id\": 5,\n" + "    \"modelName\": \"Cronos 7023L\",\n" + "    \"category\": \"Cronos L\"\n" + "  },\n" + "  {\n" + "    \"id\": 6,\n" + "    \"modelName\": \"Cronos 6001-NG\",\n" + "    \"category\": \"Cronos NG\"\n" + "  },\n" + "  {\n" + "    \"id\": 7,\n" + "    \"modelName\": \"Cronos 6003-NG\",\n" + "    \"category\": \"Cronos NG\"\n" + "  },\n" + "  {\n" + "    \"id\": 8,\n" + "    \"modelName\": \"Cronos 6021-NG\",\n" + "    \"category\": \"Cronos NG\"\n" + "  },\n" + "  {\n" + "    \"id\": 9,\n" + "    \"modelName\": \"Cronos 6031-NG\",\n" + "    \"category\": \"Cronos NG\"\n" + "  },\n" + "  {\n" + "    \"id\": 10,\n" + "    \"modelName\": \"Cronos 7021-NG\",\n" + "    \"category\": \"Cronos NG\"\n" + "  },\n" + "  {\n" + "    \"id\": 11,\n" + "    \"modelName\": \"Cronos 7023-NG\",\n" + "    \"category\": \"Cronos NG\"\n" + "  },\n" + "  {\n" + "    \"id\": 12,\n" + "    \"modelName\": \"Ares 7021\",\n" + "    \"category\": \"Ares TB\"\n" + "  },\n" + "  {\n" + "    \"id\": 13,\n" + "    \"modelName\": \"Ares 7031\",\n" + "    \"category\": \"Ares TB\"\n" + "  },\n" + "  {\n" + "    \"id\": 14,\n" + "    \"modelName\": \"Ares 7023\",\n" + "    \"category\": \"Ares TB\"\n" + "  },\n" + "  {\n" + "    \"id\": 15,\n" + "    \"modelName\": \"Ares 8023 15\",\n" + "    \"category\": \"Ares THS\"\n" + "  },\n" + "  {\n" + "    \"id\": 16,\n" + "    \"modelName\": \"Ares 8023 200\",\n" + "    \"category\": \"Ares THS\"\n" + "  },\n" + "  {\n" + "    \"id\": 17,\n" + "    \"modelName\": \"Ares 8023 2,5\",\n" + "    \"category\": \"Ares THS\"\n" + "  }\n" + "]";
        LineDTO mockLine = new LineDTO("Ares" , (short) 1);
        CategoryDTO mockCategory = new CategoryDTO("AresTB" , (short) 1);


        when(response.readEntity(String.class)).thenReturn(jsonResponse);

        List<ModelDTO> result = service.getAllMeterModels(mockCategory);

        assertNotNull(result);
        assertEquals(17 , result.size());

        ModelDTO mockModel = new ModelDTO("Ares 7021" , (short) 12);
        assertEquals(12 , mockModel.getId());
        assertEquals("Ares 7021" , mockModel.getModelName());

        verify(client).target(eq("http://localhost:4455/api/models" + "/" + mockCategory.getCategoryName()));
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }

}
