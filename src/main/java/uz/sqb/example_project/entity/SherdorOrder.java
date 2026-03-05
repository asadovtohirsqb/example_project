package uz.sqb.example_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Builder(
//        builderMethodName = "sherdorBuilder",
//        buildMethodName = "sherdorBuild"
//)
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "sherdor_orders")
public class SherdorOrder extends Order{
    private String tariffType;
    private String cardNumber;
}
//172.25.43.214:2717 - shina api