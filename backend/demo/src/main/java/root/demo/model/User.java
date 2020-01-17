package root.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String lastname;
    private String city;
    private String country;
    private String email;
    private Boolean reviewer;
    private String title;
    private Boolean active = true;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ScientificArea> scientificAreaList;
    private RoleEnum roleEnum;

    public User(){}

    public User(String username, String lastname, String city, String country, String email, Boolean reviewer, String title, Boolean active) {
        this.username = username;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.email = email;
        this.reviewer = reviewer;
        this.title = title;
        this.active = active;
    }

    public void setScientificAreaList(List<ScientificArea> scientificAreaList) {
        this.scientificAreaList = scientificAreaList;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getReviewer() {
        return reviewer;
    }

    public void setReviewer(Boolean reviewer) {
        this.reviewer = reviewer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ScientificArea> getScientificAreaList() {
        return scientificAreaList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
