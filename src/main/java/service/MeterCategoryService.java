package service;

import com.br.eletra.models.MeterCategoryEntity;
import com.br.eletra.models.MeterLineEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

public class MeterCategoryService {

    private static final String BASE_URL = "http://localhost:4455/api/categories";

    public List<MeterCategoryEntity> getAllMeterCategories (MeterLineEntity selectedLine){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gsonCat = new Gson();
        Type categoryListType = new TypeToken<List<MeterCategoryEntity>>() {
        }.getType();
        List<MeterCategoryEntity> meterList = gsonCat.fromJson(response.readEntity(String.class), categoryListType);
        List<MeterCategoryEntity> catList = new ArrayList<>();
        for (MeterCategoryEntity category : meterList) {
            if(category.getLine().equals(selectedLine.toString())){
                catList.add(category);
            }
        }
        return catList;
    }
}
