package ru.job4j.cars;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bodytype")
public class BodytypeEntity implements ProjectCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "btype", fetch = FetchType.EAGER)
    private Set<CarEntity> car;

    public BodytypeEntity() {

    }

    public BodytypeEntity(String name) {
        this.name = name;
    }

    public Set<CarEntity> getCar() {
        return car;
    }

    public void setCar(Set<CarEntity> car) {
        this.car = car;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BodytypeEntity that = (BodytypeEntity) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
