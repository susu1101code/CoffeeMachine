package com.jackson.coffee_kiosk.model.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.coffee_kiosk.model.entity.Coffees;
import com.jackson.coffee_kiosk.model.entity.MenuItem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MenuLoader implements MenuProvider {

    public List<MenuItem> load(String fileName) {
        List<MenuItem> verifiedMenu = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) return verifiedMenu;

            List<MenuItem> rawItems = mapper.readValue(inputStream, new TypeReference<List<MenuItem>>() {
            });

            for (MenuItem item : rawItems) {
                // Factory uses lowercase
                if (Coffees.getFactory(item.getName().toLowerCase()) != null) {
                    verifiedMenu.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifiedMenu;
    }


}
