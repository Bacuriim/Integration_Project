package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.CategoryDTO;
import dto.LineDTO;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

public class MeterCategoryService {

    private static final String BASE_URL = "http://localhost:4455/api/categories";

    public List<CategoryDTO> getAllMeterCategories(LineDTO selectedLine){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL + "/" + selectedLine.getLineName());
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gsonCat = new Gson();
        Type categoryListType = new TypeToken<List<CategoryDTO>>() {
        }.getType();
        List<CategoryDTO> catList = gsonCat.fromJson(response.readEntity(String.class), categoryListType);
        return catList;
    }
}
