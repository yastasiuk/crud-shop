package com.yastasiuk.shop;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

@RestController
public class ProductsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/greeting")
    public List<Product> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        List<Product> products = new ArrayList<Product>();
        products.add(
            new Product(
                Long.toString(counter.incrementAndGet()),
                String.format(template, name)
            )
        );
        return products;
    }
}
