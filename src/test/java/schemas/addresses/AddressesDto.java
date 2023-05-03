package schemas.addresses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddressesDto {

    int id;
    String city;
    String country;
    String street;
    String zip;
    int contactId;


}
/*
* id	integer($int32)
city	string
example: Berlin
country	string
example: Germany
street	string
example: Friedrichstraße 176-179
zip	string
example: 10117
contactId	integer($int32)
* */