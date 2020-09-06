package japbook.jpashop.Domain;

import lombok.Getter;

import javax.persistence.Embeddable;


//내장 될 수 있다
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
