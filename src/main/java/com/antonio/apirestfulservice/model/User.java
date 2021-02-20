package com.antonio.apirestfulservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id", unique=true, nullable=false)
    private Long id;
    
    @Column(name="login")
    private String loginName;
    
    @Column(name="password")
    private String password;
    
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE })
    private List<PersonalRoutine> personalRoutines;
    
    @Column(name="zpower")
    private int zpower;

    @Column(name="image")
    private String image;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<PersonalRoutine> getPersonalRoutines() {
        return personalRoutines;
    }

    public void setPersonalRoutines(List<PersonalRoutine> personalRoutines) {
        this.personalRoutines = personalRoutines;
        for(PersonalRoutine pr:personalRoutines){
            pr.setUser(this);
        }
    }
    
    public int getZpower() {
        return zpower;
    }

    public void setZpower(int zpower) {
        this.zpower = zpower;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", loginName=" + loginName + ", password=" + password + ", personalRoutines=" + personalRoutines + ", zpower=" + zpower + ", image=" + image + '}';
    }
    
    
}
