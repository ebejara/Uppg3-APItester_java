import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeStoreApiTest {
    private static final Logger logger = LoggerFactory.getLogger(FakeStoreApiTest.class);

    @Test
    public void getProductsReturns200() throws Exception {
        logger.info("Starting API test...");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fakestoreapi.com/products"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Kontrollera statuskod
        assertEquals(200, response.statusCode());

        // Extra kontroller
        assertTrue(response.body().length() > 0);
        assertEquals("application/json; charset=utf-8", response.headers().firstValue("content-type").orElse(""));

        logger.info("API test completed with status: {}", response.statusCode());
    }
}