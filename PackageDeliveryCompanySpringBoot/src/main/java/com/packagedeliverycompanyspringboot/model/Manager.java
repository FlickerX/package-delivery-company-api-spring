package com.packagedeliverycompanyspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Manager extends User{
    private boolean isAdmin;

    @JsonIgnore
    @ManyToMany(mappedBy = "managers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Destination> destinations;

    public Manager(String login, String password, String name, String surname, LocalDate birthday, String phoneNo, Double salary, boolean isAdmin) {
        super(login, password, name, surname, birthday, phoneNo, salary);
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return  getName() + " " +
                getSurname() +
                ", Username = " + getLogin() +
                ", Admin = " + isAdmin;
    }
}
