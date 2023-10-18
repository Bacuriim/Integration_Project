package com.br.eletra.services;

import com.br.eletra.dto.CategoryDTO;
import com.br.eletra.dto.LineDTO;
import com.br.eletra.service.MeterCategoryService;
import com.br.eletra.service.MeterLineService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.lang.reflect.Type;
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
    public void getAllMeterCategoriesAndConvertToStringTest01() {
        // Given
        String jsonResponse = "[{\"id\":2,\"categoryName\":\"Cronos L\",\"line\":\"Cronos\"},{\"id\":3,\"categoryName\":\"Cronos NG\",\"line\": \"Cronos\"},{\"id\": 4,\"categoryName\":\"Ares TB\",\"line\": \"Ares\"},{\"id\":5,\"categoryName\":\"Ares THS\",\"line\":\"Ares\"},{\"id\":1,\"categoryName\":\"Cronos Old\",\"line\": \"Cronos\"}]";
        LineDTO mockLine = new LineDTO("Ares" , (short) 1);
        Gson mockGson = new Gson();
        when(response.readEntity(String.class)).thenReturn(jsonResponse);
        Type mockCategoryListType = new TypeToken<List<CategoryDTO>>() {
        }.getType();

        // When
        List<CategoryDTO> result = service.getAllMeterCategories(mockLine);
        List<CategoryDTO> mockCategoryList = mockGson.fromJson(response.readEntity(String.class) , mockCategoryListType);

        // Then
        assertNotNull(result);
        assertEquals(response.readEntity(String.class) , jsonResponse);
        assertEquals(5 , result.size());
        assertEquals(result.toString() , mockCategoryList.toString());
        verify(client).target(eq("http://localhost:4455/api/categories" + "/" + mockLine));
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }

    @Test
    public void getAllMeterCategoriesAndConvertToStringTest02() {
        // Given
        String jsonResponse = "[{\"id\":2,\"categoryName\":\"Cronos L\",\"line\":\"Cronos\"},{\"id\":3,\"categoryName\":\"Cronos NG\",\"line\": \"Cronos\"},{\"id\": 4,\"categoryName\":\"Ares TB\",\"line\": \"Ares\"},{\"id\":5,\"categoryName\":\"Ares THS\",\"line\":\"Ares\"},{\"id\":1,\"categoryName\":\"Cronos Old\",\"line\": \"Cronos\"}]";
        LineDTO mockLine = new LineDTO("1" , (short) 1);
        Gson mockGson = new Gson();
        Type mockCategoryListType = new TypeToken<List<CategoryDTO>>() {
        }.getType();


        // When
        when(response.readEntity(String.class)).thenReturn(jsonResponse);
        List<CategoryDTO> result = service.getAllMeterCategories(mockLine);
        List<CategoryDTO> mockCategoryList = mockGson.fromJson(response.readEntity(String.class) , mockCategoryListType);

        // Then
        assertNotNull(result);
        assertEquals(response.readEntity(String.class) , jsonResponse);
        assertEquals(5 , result.size());
        assertEquals(result.toString() , mockCategoryList.toString());
        verify(client).target(eq("http://localhost:4455/api/categories" + "/" + mockLine));
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }
}
