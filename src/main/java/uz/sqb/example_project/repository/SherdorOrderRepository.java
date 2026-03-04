package uz.sqb.example_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sqb.example_project.entity.SherdorOrder;

public interface SherdorOrderRepository extends JpaRepository<SherdorOrder, Integer> {
}
