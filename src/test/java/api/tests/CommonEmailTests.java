package api.tests;

import api.email.EmailApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonEmailTests extends EmailApi {

    @Test
    public void createEditDeleteNewEmail() {
        createEmail(201);
        Response actualResponse = getEmails(200);
        int emailId = actualResponse.jsonPath().getInt("[0].id");
        String email = actualResponse.jsonPath().getString("[0].email");
        Assert.assertEquals(email, randomDataBodyForCreateEmail().getEmail(), "Email not equal");


        editExistingEmail(200, emailId);
        Response editedEmails = getEmails(200);
        String editedEmail = editedEmails.jsonPath().getString("[0].email");
        Assert.assertEquals(editedEmail, randomDataBodyForEditEmail(emailId).getEmail(), "Email not equal");

        deleteExistingEmail(200, emailId);
        Response actualDeletedResponse = getEmail(500, emailId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

    }


}
