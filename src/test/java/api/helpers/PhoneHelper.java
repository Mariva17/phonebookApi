package api.helpers;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class PhoneHelper extends PhoneApi {

    public int createPhone(Integer contactId) {
        createPhone(201, contactId);
        Response actualResponse = getAllPhones(200, contactId);
        int phoneId = actualResponse.jsonPath().getInt("[0].id");
        String countryCode = actualResponse.jsonPath().getString("[0].countryCode");
        String phone = actualResponse.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(countryCode, randomDataBodyForCreatePhone(contactId).getCountryCode(), "CountryCode is not equal");
        Assert.assertEquals(phone, randomDataBodyForCreatePhone(contactId).getPhoneNumber(), "PhoneNumber is not equal");
        return phoneId;
    }

    public void editPhone(Integer phoneId, Integer contactId) {
        editExistingPhone(200, phoneId, contactId);
        Response editedPhones = getAllPhones(200, contactId);
        String editedCountryCode = editedPhones.jsonPath().getString("[0].countryCode");
        String editedPhone = editedPhones.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(editedCountryCode, randomDataBodyForEditPhone(phoneId, contactId).getCountryCode(), "Changed CountryCode is not equal");
        Assert.assertEquals(editedPhone, randomDataBodyForEditPhone(phoneId, contactId).getPhoneNumber(), "Changed PhoneNumber is not equal");

    }

    public void deletePhone(Integer phoneId) {
        deleteExistingPhone(200, phoneId);
        Response actualDeletedResponse = getPhone(500, phoneId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");
    }

}
