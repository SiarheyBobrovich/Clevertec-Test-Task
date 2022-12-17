package by.bobrovich.market.dao;

import by.bobrovich.market.api.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileProductDaoTest {

    private final FileProductDao productDao;
    private FileProductDaoTest() throws IOException {
        productDao = new FileProductDao("src/main/resources/in_memory_products.csv");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 29})
    void getById(int id) {
        Product product = productDao.getById(id).orElse(null);

        assertNotNull(product);
        assertEquals(id, product.getId());
        assertNotNull(product.getDescription());
        assertNotNull(product.getPrice());
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 30})
    void getByIdFailed(int id) {
        Product product = productDao.getById(id).orElse(null);

        assertNull(product);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 15, 25, 28})
    void exists(int id) {
        assertTrue(productDao.exists(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE})
    void existsFailed(int id) {
        assertFalse(productDao.exists(id));
    }
}