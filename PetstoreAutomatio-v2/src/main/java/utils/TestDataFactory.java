package utils;

import models.Order;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataFactory {
//user Stuff
    private static int idCounter = 1000;
    // Store stuff
    private static long orderIdCounter = 5000;
// User Api test Data
    public static User createRandomUser() {
        String unique = UUID.randomUUID().toString().substring(0, 6);

        return new User(
                idCounter++,
                "Piyush_user_" + unique,
                "PFirst_" + unique,
                "VLast_" + unique,
                "piyush" + unique + "@Bms.com",
                "pass@" + unique,
                "9" + (int)(Math.random() * 1_000_000_000),
                1
        );
    }

    public static List<User> createUserList(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(createRandomUser());
        }
        return users;
    }

    // Store API test Data.....i should add or nottttt......;-] ?????
    public static Order createValidOrder(long petId) {
        Order order = new Order();
        order.setId(orderIdCounter++);
        order.setPetId(petId);
        order.setQuantity(1);
        order.setStatus("placed");
        order.setComplete(true);
        return order;
    }
}
