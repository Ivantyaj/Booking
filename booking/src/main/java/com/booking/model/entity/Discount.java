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

    @OneToMany(mappedBy = "discount")
    private Set<Client> clients = new HashSet<>();

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (!Objects.equals(id, discount.id)) return false;
        if (!Objects.equals(percent, discount.percent)) return false;
        if (!Objects.equals(minVisitCount, discount.minVisitCount))
            return false;
        if (!Objects.equals(description, discount.description))
            return false;
        return Objects.equals(clients, discount.clients);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (minVisitCount != null ? minVisitCount.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (clients != null ? clients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", percent=" + percent +
                ", minVisitCount=" + minVisitCount +
                ", description='" + description + '\'' +
                ", clients=" + clients +
                '}';
    }
}
