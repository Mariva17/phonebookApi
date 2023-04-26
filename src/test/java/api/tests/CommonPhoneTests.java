package api.tests;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonPhoneTests {

    PhoneApi phoneApi = new PhoneApi();
    Integer contactId = 4911;

    @Test
    public void createEditDeleteNewPhone() {
        phoneApi.createPhone(201, contactId);
        Response actualResponse = phoneApi.getAllPhones(200, contactId);
        int phoneId = actualResponse.jsonPath().getInt("[0].id");
        String countryCode = actualResponse.jsonPath().getString("[0].countryCode");
        String phone = actualResponse.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(countryCode, phoneApi.randomDataBodyForCreatePhone(contactId).getCountryCode(), "CountryCode is not equal");
        Assert.assertEquals(phone, phoneApi.randomDataBodyForCreatePhone(contactId).getPhoneNumber(), "PhoneNumber is not equal");


        phoneApi.editExistingPhone(200, phoneId, contactId);
        Response editedPhones = phoneApi.getAllPhones(200, contactId);
        String editedCountryCode = editedPhones.jsonPath().getString("[0].countryCode");
        String editedPhone = editedPhones.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(editedCountryCode, phoneApi.randomDataBodyForEditPhone(phoneId, contactId).getCountryCode(), "Changed CountryCode is not equal");
        Assert.assertEquals(editedPhone, phoneApi.randomDataBodyForEditPhone(phoneId, contactId).getPhoneNumber(), "Changed PhoneNumber is not equal");

        phoneApi.deleteExistingPhone(200, phoneId);
        Response actualDeletedResponse = phoneApi.getPhone(500, phoneId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");

    }

}
