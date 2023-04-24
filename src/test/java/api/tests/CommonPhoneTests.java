package api.tests;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonPhoneTests extends PhoneApi {

    @Test
    public void createEditDeleteNewPhone() {
        createPhone(201);
        Response actualResponse = getAllPhones(200);
        int phoneId = actualResponse.jsonPath().getInt("[0].id");
        String countryCode = actualResponse.jsonPath().getString("[0].countryCode");
        String phone = actualResponse.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(countryCode, randomDataBodyForCreatePhone().getCountryCode(), "CountryCode is not equal");
        Assert.assertEquals(phone, randomDataBodyForCreatePhone().getPhoneNumber(), "PhoneNumber is not equal");


        editExistingPhone(200, phoneId);
        Response editedPhones = getAllPhones(200);
        String editedCountryCode = editedPhones.jsonPath().getString("[0].countryCode");
        String editedPhone = editedPhones.jsonPath().getString("[0].phoneNumber");
        Assert.assertEquals(editedCountryCode, randomDataBodyForEditPhone(phoneId).getCountryCode(), "Changed CountryCode is not equal");
        Assert.assertEquals(editedPhone, randomDataBodyForEditPhone(phoneId).getPhoneNumber(), "Changed PhoneNumber is not equal");

        deleteExistingPhone(200, phoneId);
        Response actualDeletedResponse = getPhone(500, phoneId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");

    }

}
