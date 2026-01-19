package tests;

import api.StoreApi;
import models.Order;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestDataFactory;
import io.restassured.response.Response;

public class StoreApiTest {

    @Test
    public void getInventoryTest() {
        Response response = StoreApi.getInventory();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void placeAndGetOrderTest() {

        Order order = TestDataFactory.createValidOrder(1);

        Response placeResponse = StoreApi.placeOrder(order);
        Assert.assertEquals(placeResponse.getStatusCode(), 200);

        Response getResponse = StoreApi.getOrderById(order.getId());
        Assert.assertEquals(getResponse.getStatusCode(), 200);
    }

    @Test
    public void deleteOrderTest() {

        Order order = TestDataFactory.createValidOrder(1);
        StoreApi.placeOrder(order);

        Response response = StoreApi.deleteOrder(order.getId());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}