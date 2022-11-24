package com.example.productshopxml.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USER_XML_PATH =
            Path.of("src", "main", "resources", "dbContent", "users.xml");
    public static final Path CATEGORIES_XML_PATH =
            Path.of("src", "main", "resources", "dbContent", "categories.xml");
    public static final Path PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "dbContent", "products.xml");

    public static final Path PRODUCTS_WITH_NO_BUYERS_IN_RANGE_XML_PATH =
            Path.of("src", "main", "resources", "output", "products-in-range.xml");
    public static final Path USERS_WITH_SOLD_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "output", "users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "output", "categories-by-products.xml");
    public static final Path USERS_AND_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "output", "users-and-products.xml");


}
