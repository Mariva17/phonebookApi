package api.tests;

import api.email.EmailApi;
import api.helpers.EmailHelper;
import org.testng.annotations.Test;

public class CommonEmailTests extends EmailApi {

    Integer contactId = 4911;

    EmailHelper emailHelper = new EmailHelper();

    @Test
    public void createEditDeleteNewEmail() {
        Integer emailId = emailHelper.createEmail(contactId);

        emailHelper.editEmail(emailId, contactId);

        emailHelper.deleteEmail(emailId);
    }


}
