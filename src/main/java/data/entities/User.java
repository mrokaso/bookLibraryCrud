package data.entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BookUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialMode;

    private String socialId;

    private String name;

    private String surname;

    private Date birthDate;

    private String imageURL;

    private String email;

    private boolean admin;

    public User(){}

    public User(String socialMode, String socialId, String name, String surname, Date birthDate, String imageURL, String email){
        this.socialMode = socialMode;
        this.socialId = socialId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.imageURL = imageURL;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialMode() {
        return socialMode;
    }

    public void setSocialMode(String socialMode) {
        this.socialMode = socialMode;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
