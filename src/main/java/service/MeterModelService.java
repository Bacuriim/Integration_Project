package service;

import com.br.eletra.models.MeterCategoryEntity;
import com.br.eletra.models.MeterModelEntity;
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

public class MeterModelService {

    private static final String BASE_URL = "http://localhost:4455/api/models";

    public List<MeterModelEntity> getAllMeterModels (MeterCategoryEntity category){
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gsonMod = new Gson();
        Type ModelListType = new TypeToken<List<MeterModelEntity>>() {
        }.getType();
        List<MeterModelEntity> meterList = gsonMod.fromJson(response.readEntity(String.class), ModelListType);
        List<MeterModelEntity> modList = new ArrayList<>();
        for (MeterModelEntity model : meterList) {
            if(model.getCategory().equals(category.toString())){
                modList.add(model);
            }
        }
        return modList;
    }
}