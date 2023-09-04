package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.CategoryDTO;
import dto.LineDTO;
import org.glassfish.jersey.client.ClientConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MeterLineService {

    private static final String BASE_URL = "http://localhost:4455/api/lines";

    public List<LineDTO> getAllMeterLines() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gson = new Gson();
        Type lineListType = new TypeToken<List<LineDTO>>() {
        }.getType();
        List<LineDTO> list = gson.fromJson(response.readEntity(String.class), lineListType);
        return list;
    }

}
