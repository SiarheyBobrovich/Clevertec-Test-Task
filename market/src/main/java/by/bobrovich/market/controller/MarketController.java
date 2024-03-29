package by.bobrovich.market.controller;

import by.bobrovich.market.api.Order;
import by.bobrovich.market.api.Receipt;
import by.bobrovich.market.data.MarketOrder;
import by.bobrovich.market.service.api.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/check")
@RequiredArgsConstructor
public class MarketController {

    private final ReceiptService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Receipt> getReceipt(@RequestParam List<Integer> itemId,
                                              @RequestParam(required = false) Integer card) {
        Order order = MarketOrder.builder()
                .addItemsId(itemId)
                .addDiscountCard(card)
                .build();
        Receipt receipt = service.getReceipt(order);
        return ResponseEntity.ok().body(receipt);
    }
}