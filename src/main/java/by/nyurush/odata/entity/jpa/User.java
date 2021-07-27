package by.nyurush.odata.entity.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@SuperBuilder
@ToString
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = EAGER)
    private Collection<Pet> pets;

}
