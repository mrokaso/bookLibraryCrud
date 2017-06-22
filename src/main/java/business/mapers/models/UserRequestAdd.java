package business.mapers.models;


import java.util.Date;

public class UserRequestAdd {

    private String socialMode;

    private String socialId;

    private String name;

    private String surname;

    private Date birthDate;

    private String imageURL;

    private String email;

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
}
