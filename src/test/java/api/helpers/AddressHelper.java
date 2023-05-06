package api.helpers;

import api.addresses.AddressesApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class AddressHelper extends AddressesApi {

    public int createAddress(Integer contactId) {
        createAddress(201, contactId);
        Response actualResponse = getAllAddresses(200, contactId);
        int addressId = actualResponse.jsonPath().getInt("[0].id");
        String city = actualResponse.jsonPath().getString("[0].city");
        String country = actualResponse.jsonPath().getString("[0].country");
        String street = actualResponse.jsonPath().getString("[0].street");
        String zip = actualResponse.jsonPath().getString("[0].zip");

        Assert.assertEquals(city, randomDataBodyForCreateAddress(contactId).getCity(), "City is not equal");
        Assert.assertEquals(country, randomDataBodyForCreateAddress(contactId).getCountry(), "Country is not equal");
        Assert.assertEquals(street, randomDataBodyForCreateAddress(contactId).getStreet(), "Street is not equal");
        Assert.assertEquals(zip, randomDataBodyForCreateAddress(contactId).getZip(), "Zip is not equal");
        return addressId;
    }

    public void editAddress(Integer addressId, Integer contactId) {
        editExistingAddress(200, addressId, contactId);
        Response editedAddress = getAllAddresses(200, contactId);
        String editedCity = editedAddress.jsonPath().getString("[0].city");
        String editedStreet = editedAddress.jsonPath().getString("[0].street");
        String editedZip = editedAddress.jsonPath().getString("[0].zip");
        Assert.assertEquals(editedCity, randomDataBodyForEditAddress(addressId, contactId).getCity(), "Changed City is not equal");
        Assert.assertEquals(editedStreet, randomDataBodyForEditAddress(addressId, contactId).getStreet(), "Changed Street is not equal");
        Assert.assertEquals(editedZip, randomDataBodyForEditAddress(addressId, contactId).getZip(), "Changed Zip is not equal");

    }

    public void deleteAddress(Integer addressId) {
        deleteExistingAddress(200, addressId);
        Response actualDeletedResponse = getAddress(500, addressId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This address doesn't exist in our DB");
    }

}
