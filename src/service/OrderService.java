package service;

import entities.Order;
import entities.OrderItem;

import exceptions.DuplicateResourceException;
import exceptions.EmptyOrderException;
import exceptions.InsufficientStockException;
import exceptions.InvalidDataException;
import exceptions.ResourceNotFoundException;

import repository.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {

        if (order == null) {
            throw new InvalidDataException("Order cannot be null.");
        }

        if (order.getOrderCode() == null || order.getOrderCode().isBlank()) {
            throw new InvalidDataException("Order code is required.");
        }

        if (orderRepository.existsByCode(order.getOrderCode())) {
            throw new DuplicateResourceException("Order already exists.");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new EmptyOrderException("Order must contain at least one item.");
        }

        for (OrderItem item : order.getItems()) {

            if (item == null) {
                throw new InvalidDataException("Order item cannot be null.");
            }

            if (item.getProduct() == null) {
                throw new InvalidDataException("Order item product cannot be null.");
            }

            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new InvalidDataException("Item quantity must be greater than zero.");
            }

            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new InsufficientStockException("Insufficient product stock.");
            }
        }

        orderRepository.save(order);
    }

    public Order findByCode(String orderCode) {

        if (orderCode == null || orderCode.isBlank()) {
            throw new InvalidDataException("Order code is required.");
        }

        return orderRepository.findByCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found."));
    }

    public void delete(String orderCode) {

        findByCode(orderCode);
        orderRepository.delete(orderCode);
    }

    public boolean existsByCode(String orderCode) {

        if (orderCode == null || orderCode.isBlank()) {
            throw new InvalidDataException("Order code is required.");
        }

        return orderRepository.existsByCode(orderCode);
    }
}