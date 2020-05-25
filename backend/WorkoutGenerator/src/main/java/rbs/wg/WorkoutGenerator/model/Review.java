package rbs.wg.WorkoutGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rbs.wg.WorkoutGenerator.dto.ReviewDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer exertionLevel;

    @Column(nullable = false)
    private Integer rating;

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Workout workout;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private AppUser user;

    public Review(ReviewDto reviewDto, Workout workout, AppUser user) {
        this.workout = workout;
        this.exertionLevel = reviewDto.getExertionLevel();
        this.rating = reviewDto.getRating();
        this.user = user;
    }
}
