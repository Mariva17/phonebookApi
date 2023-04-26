package api.tests;

import api.email.EmailApi;
import api.helpers.EmailHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonEmailTests {

    EmailApi emailApi = new EmailApi();
    Integer contactId = 4911;

    EmailHelper emailHelper = new EmailHelper();

    @Test
    public void createEditDeleteNewEmail() {
        emailApi.createEmail(201, contactId);
        Response actualResponse = emailApi.getEmails(200, contactId);
        int emailId = actualResponse.jsonPath().getInt("[0].id");
        String email = actualResponse.jsonPath().getString("[0].email");
        Assert.assertEquals(email, emailApi.randomDataBodyForCreateEmail(contactId).getEmail(), "Email not equal");


        emailApi.editExistingEmail(200, emailId, contactId);
        Response editedEmails = emailApi.getEmails(200, contactId);
        String editedEmail = editedEmails.jsonPath().getString("[0].email");
        Assert.assertEquals(editedEmail, emailApi.randomDataBodyForEditEmail(emailId, contactId).getEmail(), "Email not equal");

        emailHelper.deleteEmail(emailId);
    }


}
