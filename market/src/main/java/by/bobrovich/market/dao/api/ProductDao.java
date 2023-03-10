package by.bobrovich.market.dao.api;

import by.bobrovich.market.entity.MarketProduct;
import by.bobrovich.market.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    /**
     * Returns saved product
     *
     * @param id - product id
     * @return - Product if contains
     * @throws ProductNotFoundException - if not found
     */
    Optional<MarketProduct> findById(Integer id);

    /**
     * Check Product in repository
     * @param id - Product id
     * @return - true if exists
     */
    boolean exists(Integer id);

    /**
     * Check Product in repository
     * @param id - Product id
     * @param quantity - Product quantity
     * @return - true if exists, and quantity is available
     */
    boolean isExistsAndQuantityAvailable(Integer id, Integer quantity);

    /**
     * Update current product
     * @param product - updated
     */
    void update(MarketProduct product);

    void delete(Integer id);

    void save(MarketProduct product);

    List<MarketProduct> findAll();
}