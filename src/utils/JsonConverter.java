package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Address;

public class JsonConverter {


    public Address toObject(String jsonString, Class<Address> cepClass) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Address objectAddress = gson.fromJson(jsonString, Address.class);
        System.out.println(objectAddress);

        return objectAddress;


    }
}
