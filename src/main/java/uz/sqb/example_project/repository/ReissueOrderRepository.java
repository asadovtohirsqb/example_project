package uz.sqb.example_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sqb.example_project.entity.ReissueOrder;

public interface ReissueOrderRepository extends JpaRepository<ReissueOrder, Integer> {
}
