package myshop.shopproject.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue(value = "M")
@Getter
@Setter
@Entity
public class Movie extends Item {
    private String director;
    private String actor;

}


