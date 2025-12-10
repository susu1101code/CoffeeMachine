package com.jackson.coffee_kiosk.model.repository;

import com.jackson.coffee_kiosk.model.entity.MenuItem;

import java.util.List;

public interface MenuProvider {
    List<MenuItem> load(String path);
}
