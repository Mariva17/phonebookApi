package api.tests;

import api.email.EmailApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonEmailTests extends EmailApi {

    @Test
    public void createEditDeleteNewEmail() {

        createEmail(201);

        Response actualResponse = getEmail(200, 4374);

        Assert.assertEquals(actualResponse.jsonPath().getString("email"), randomDataBodyForCreateEmail().getEmail(), "Email not equal");


    }

}
