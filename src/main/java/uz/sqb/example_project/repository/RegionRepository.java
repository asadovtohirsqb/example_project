package uz.sqb.example_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sqb.example_project.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
