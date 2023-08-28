package service;

import com.br.eletra.models.MeterLineEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    public List<MeterLineEntity> getAllMeterLines() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget myResource = client.target(BASE_URL);
        Invocation.Builder builder = myResource.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        Gson gson = new Gson();
        Type lineListType = new TypeToken <List<MeterLineEntity>>() {}.getType();
        List<MeterLineEntity> list =  gson.fromJson(response.readEntity(String.class),lineListType);
        List<MeterLineEntity> meterList = new ArrayList<>();
        for (MeterLineEntity line : list) {
            meterList.add(line);
        }
        return meterList;
    }

}
