package com.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room_type")
public class RoomType {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "human_amount")
    private Long humanAmount;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private Type type;

    @Column(name = "description", length = 1000)
    private String description;

    public RoomType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHumanAmount() {
        return humanAmount;
    }

    public void setHumanAmount(Long humanAmount) {
        this.humanAmount = humanAmount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        RoomType roomType = (RoomType) o;
        return Objects.equals(id, roomType.id) &&
                Objects.equals(humanAmount, roomType.humanAmount) &&
                type == roomType.type &&
                Objects.equals(description, roomType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, humanAmount, type, description);
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", humanAmount=" + humanAmount +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
