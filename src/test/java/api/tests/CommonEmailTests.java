package api.tests;

import api.email.EmailApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonEmailTests extends EmailApi {

    @Test
    public void createEditDeleteNewEmail() {
        createEmail(201);
        Response actualResponse = getEmail(200, 579);
        Assert.assertEquals(actualResponse.jsonPath().getString("email"), randomDataBodyForCreateEmail().getEmail(), "Email not equal");


        editExistingEmail(200, 568);
        Response actualExistingEmail = getEmail(200, 568);

        //    Response actualExistingEmail2 = getEmailWithParam(200, randomDataBodyForCreateEmail().getContactId());

        Assert.assertEquals(actualExistingEmail.jsonPath().getString("email"), randomDataBodyForEditEmail(568).getEmail(), "Email not equal");

        deleteExistingEmail(200, 564);
        Response actualDeletedResponse = getEmail(500, 564);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

    }

}
