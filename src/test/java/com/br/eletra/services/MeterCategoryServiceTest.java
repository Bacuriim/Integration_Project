package com.br.eletra.services;

import com.br.eletra.dto.CategoryDTO;
import com.br.eletra.dto.LineDTO;
import com.br.eletra.service.MeterCategoryService;
import com.br.eletra.service.MeterLineService;
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
public class MeterCategoryServiceTest {

    private MeterCategoryService service;
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

        service = new MeterCategoryService();
        service.setClient(client);

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
    }

    @Test
    public void getAllMeterCategoriesAndConvertToStringTest() {
        String jsonResponse = "[\n" + "  {\n" + "    \"id\": 2,\n" + "    \"categoryName\": \"Cronos L\",\n" + "    \"line\": \"Cronos\"\n" + "  },\n" + "  {\n" + "    \"id\": 3,\n" + "    \"categoryName\": \"Cronos NG\",\n" + "    \"line\": \"Cronos\"\n" + "  },\n" + "  {\n" + "    \"id\": 4,\n" + "    \"categoryName\": \"Ares TB\",\n" + "    \"line\": \"Ares\"\n" + "  },\n" + "  {\n" + "    \"id\": 5,\n" + "    \"categoryName\": \"Ares THS\",\n" + "    \"line\": \"Ares\"\n" + "  },\n" + "  {\n" + "    \"id\": 1,\n" + "    \"categoryName\": \"Cronos Old\",\n" + "    \"line\": \"Cronos\"\n" + "  }\n" + "]";
        LineDTO mockLine = new LineDTO("Ares" , (short) 1);

        when(response.readEntity(String.class)).thenReturn(jsonResponse);

        List<CategoryDTO> result = service.getAllMeterCategories(mockLine);

        assertNotNull(result);
        assertEquals(5 , result.size());

        CategoryDTO mockCategory = new CategoryDTO("Ares TB" , (short) 1);
        assertEquals(1 , mockCategory.getId());
        assertEquals("Ares TB" , mockCategory.getCategoryName());

        verify(client).target(eq("http://localhost:4455/api/categories" + "/" + mockLine));
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }
}
