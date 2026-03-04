package uz.sqb.example_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sqb.example_project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
