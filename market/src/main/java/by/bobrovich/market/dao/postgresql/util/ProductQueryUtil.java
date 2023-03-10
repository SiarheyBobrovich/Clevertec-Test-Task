package by.bobrovich.market.dao.postgresql.util;

public class ProductQueryUtil {

    public static final String SELECT_PRODUCT_BY_ID_QUERY =
            """
            SELECT
                id,
                description,
                price,
                quantity,
                is_discount
            FROM
                product
            WHERE
                id = ?;
            """;

    public static final String SELECT_PRODUCT_BY_ID_AND_QUANTITY_QUERY = """
            SELECT
                p.id,
                p.description,
                p.price,
                p.quantity,
                p.is_discount
            FROM
                product p
            WHERE
                p.id = ? AND
                p.quantity >= ?;
            """;

    public static final String UPDATE_PRODUCT_QUERY = """
            UPDATE product
                SET
                    description = ?,
                    price = ?,
                    quantity = ?,
                    is_discount = ?
                WHERE
                    id = ?;
            """;
    public static final String DELETE_PRODUCT_BY_ID = """
            DELETE FROM market.product
                WHERE id = ?;
            """;
    public static String INSERT_NEW_PRODUCT = """
            INSERT INTO
                market.product(
                    description,
                    price,
                    quantity,
                    is_discount
                )
                VALUES (?,?,?,?);
            """;
    public static String SELECT_ALL_PRODUCTS =
            """
            SELECT
                id,
                description,
                price,
                quantity,
                is_discount
            FROM
                market.product;
            """;


    private ProductQueryUtil(){}
}
