package core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Sportive extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private int age;
    private int tid;
}
