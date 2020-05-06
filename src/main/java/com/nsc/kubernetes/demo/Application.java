package com.nsc.kubernetes.demo;

import com.nsc.kubernetes.demo.model.Item;
import com.nsc.kubernetes.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {

    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void initialize() {
        Set<Item> items = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setClinicalItemId("Clinical_Item_ID_" + i);
            item.setName("Clinical_Item_Name_" + i);
            items.add(item);
        }
        itemRepository.saveAll(items);
    }
}
