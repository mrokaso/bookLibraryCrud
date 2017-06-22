package data.entities;


import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@ApiModel
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rate;

    @Column(columnDefinition = "TEXT")
    private String opinion;

    @OneToOne
    private Book book;

    public Review(){}

    public Review(int rate, String opinion, Book book){
        this.rate = rate;
        this.opinion = opinion;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
