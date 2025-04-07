package com.leonardo.product.exception;

// classe criada para lidar com as exceptions de forma melhor e bem mais
// profissional
public class ProductPurchaseException extends RuntimeException{
    public ProductPurchaseException(String message) {
        super(message);
    }
}
