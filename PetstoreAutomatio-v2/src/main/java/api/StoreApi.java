package api;

import constants.Routes;
import io.restassured.response.Response;
import models.Order;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class StoreApi {

    private StoreApi() {}

    // GET /store/inventory
    public static Response getInventory() {
        return RestUtils.get(Routes.GET_INVENTORY, Map.of());
    }

    // POST /store/order
    public static Response placeOrder(Order order) {
        return RestUtils.post(Routes.PLACE_ORDER, order);
    }

    // GET /store/order/{orderId}
    public static Response getOrderById(long orderId) {
        Map<String, String> path = new HashMap<>();
        path.put("orderId", String.valueOf(orderId));
        return RestUtils.get(Routes.GET_ORDER_BY_ID, path);
    }

    // DELETE /store/order/{orderId}
    public static Response deleteOrder(long orderId) {
        Map<String, String> path = new HashMap<>();
        path.put("orderId", String.valueOf(orderId));
        return RestUtils.delete(Routes.DELETE_ORDER, path);
    }
}