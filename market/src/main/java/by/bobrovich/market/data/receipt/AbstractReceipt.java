package by.bobrovich.market.data.receipt;

import by.bobrovich.market.api.Basket;
import by.bobrovich.market.api.Receipt;
import by.bobrovich.market.decorator.ProductQuantity;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class AbstractReceipt implements Receipt {

    protected final String blockSeparator = "--------------------------------------------";
    protected final String baseTitle =
            """
                    CASH RECEIPT
                SUPERMARKET     123
             12, MILKYWAY GALAXY/ Earth
                Tel : 123-456-7890
        CASHIER: №%d
                                  DATE:  %s
                                  TIME:  %s
        """;

    protected static final String BASE_BODY =
            """
            QTY  DESCRIPTION         PRICE      TOTAL
            """;

    private final String title;
    private final String body;

    public AbstractReceipt(LocalDateTime dateTime, Basket basket, int cashier) {
        title = String.format(
                baseTitle,
                cashier,
                dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                dateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        body = createBody(basket);
    }

    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
    public abstract String getTotal();

    private String createBody(Basket basket) {
        List<ProductQuantity> products = basket.getProducts();
        StringBuilder info = new StringBuilder(BASE_BODY);

        products.forEach(productQuantity -> {
            String description = productQuantity.getDescription();
            BigDecimal price = productQuantity.getPrice();
            BigDecimal total = productQuantity.getTotalPrice();
            int quantity = productQuantity.getQuantity();

            info.append(normalizeString(5, String.valueOf(quantity)))
                    .append(normalizeString(20, description))
                    .append("$")
                    .append(normalizeString(10, setScaleTo2(price).toString()))
                    .append("$")
                    .append(normalizeString(10, setScaleTo2(total).toString()))
                    .append('\n');
        });

        return info.toString();
    }

    @Override
    public void print(PrintStream out) {
        out.print(getTitle());
        out.println(blockSeparator);
        out.print(getBody());
        out.println(blockSeparator);
        out.println(getTotal());
    }

    protected String normalizeString(int length, String description) {
        int currentLength = description.length();
        String result;
        if (currentLength == length) {
            result = description;
        } else if (currentLength > length) {
            result = description.substring(length);
        } else {
            result = description + " ".repeat(length - currentLength);
        }

        return result;
    }

    protected BigDecimal setScaleTo2(BigDecimal decimal){
        return decimal.setScale(2, RoundingMode.HALF_UP);
    }
}