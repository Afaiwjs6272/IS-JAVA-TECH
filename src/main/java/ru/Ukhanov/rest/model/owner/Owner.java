package ru.Ukhanov.rest.model.owner;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.Ukhanov.rest.model.cats.Cat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long owner_id = 0L;

    @Column(name = "name")
    @NotEmpty
    private String ownerName;

    @Column(name = "birthday")
    private LocalDate ownerBirthday;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "roles")
    @NotBlank
    private String roles;

    @OneToMany(mappedBy = "owner", targetEntity = Cat.class)
    private List<Cat> cats = new ArrayList<>();

    public Owner() {
    }
}
