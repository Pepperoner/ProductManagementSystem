package com.management.product.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        return (object != null) && (super.equals(object) || (getClass() == object.getClass()));
    }

    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                '}';
    }
}
