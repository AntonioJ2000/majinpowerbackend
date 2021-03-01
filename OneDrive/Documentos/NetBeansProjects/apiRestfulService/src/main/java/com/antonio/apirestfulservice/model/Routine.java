package com.antonio.apirestfulservice.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id", unique=true, nullable=false)
    private Long id;
    

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;
    
    @Column(name="duration")
    private int duration;

    @Column(name="difficulty")
    private String difficulty;
    
    @Column(name="t_done")
    private int timesDone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public int getTimesDone() {
        return timesDone;
    }

    public void setTimesDone(int timesDone) {
        this.timesDone = timesDone;
    }

    @Override
    public String toString() {
        return "Routine{" + "id=" + id + ", title=" + title + ", description=" + description + ", duration=" + duration + ", difficulty=" + difficulty + ", timesDone=" + timesDone + '}';
    }

 
    
}
