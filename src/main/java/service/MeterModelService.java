package service;

import dto.ModelDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.CategoryDTO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MeterModelService {

    private static final String BASE_URL = "http://localhost:4455/api/models";

    public List<ModelDTO> getAllMeterModels(CategoryDTO category){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL + "/" + category.getCategoryName());
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gsonMod = new Gson();
        Type ModelListType = new TypeToken<List<ModelDTO>>() {
        }.getType();
        List<ModelDTO> modList = gsonMod.fromJson(response.readEntity(String.class), ModelListType);
        System.out.println(modList);
        return modList;
    }
}