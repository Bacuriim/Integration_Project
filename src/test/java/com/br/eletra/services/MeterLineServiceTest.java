package com.br.eletra.services;

import com.br.eletra.dto.LineDTO;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeterLineServiceTest {

    private MeterLineService service;
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

        service = new MeterLineService();
        service.setClient(client);

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
        when(builder.get()).thenReturn(response);
    }

    @Test
    public void getAllMeterLinesAndConvertToStringTest() {
        String jsonResponse = "[{\"id\": 1, \"name\": \"Ares\"}, {\"id\": 2, \"name\": \"Cronos\"}]";
        when(response.readEntity(String.class)).thenReturn(jsonResponse);

        List<LineDTO> result = service.getAllMeterLines();

        assertNotNull(result);
        assertEquals(2, result.size());

        LineDTO line1 = new LineDTO("Ares" , (short) 1);
        assertEquals(1, line1.getId());
        assertEquals("Ares", line1.getLineName());

        verify(client).target(eq("http://localhost:4455/api/lines"));
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get();
    }

}
