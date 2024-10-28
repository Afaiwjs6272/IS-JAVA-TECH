package ru.Ukhanov.rest.model.cats;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.Ukhanov.rest.model.cats.enums.Breed;
import ru.Ukhanov.rest.model.cats.enums.Color;
import ru.Ukhanov.rest.model.owner.Owner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id = 0L;

    @Column(name = "name")
    @NotEmpty
    private String catName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "breed")
    private Breed breed;

    @Column(name = "color")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<Cat> friendList = new HashSet<>();

    public Cat() {
    }
}
