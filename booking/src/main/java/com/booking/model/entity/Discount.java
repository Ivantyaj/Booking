package com.booking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "discount")
public class Discount {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percent")
    private Long percent;

    @Column(name = "min_visiting_count")
    private Long minVisitCount;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "discounts")
    private Set<Client> client = new HashSet<>();

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public Long getMinVisitCount() {
        return minVisitCount;
    }

    public void setMinVisitCount(Long minVisitCount) {
        this.minVisitCount = minVisitCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) &&
                Objects.equals(percent, discount.percent) &&
                Objects.equals(minVisitCount, discount.minVisitCount) &&
                Objects.equals(description, discount.description) &&
                Objects.equals(client, discount.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percent, minVisitCount, description, client);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", percent=" + percent +
                ", minVisitCount=" + minVisitCount +
                ", description='" + description + '\'' +
                ", client=" + client +
                '}';
    }
}
