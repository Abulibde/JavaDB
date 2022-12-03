package com.example.football.models.entity;


import javax.persistence.*;

@Entity
@Table(name = "stats")
public class Stat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private float shooting;

    @Column(nullable = false)
    private float passing;

    @Column(nullable = false)
    private float endurance;

    public Stat(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }
}
