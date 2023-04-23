package api.email;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.email.EmailDto;

public class EmailApi extends ApiBase {

    Response response;

    EmailDto dto;

    Faker faker = new Faker();

    public EmailDto randomDataBodyForCreateEmail() {
        dto = new EmailDto();
        dto.setEmail("m_vhhhh@gmail.com");
        dto.setContactId(4805);
        return dto;
    }

    public EmailDto randomDataBodyForEditEmail(Integer emailId) {
        dto = new EmailDto();
        dto.setId(emailId);
        dto.setEmail("mar_bag_b@gmail.com");
        dto.setContactId(4805);
        return dto;
    }

    public Response createEmail(Integer code) {
        String endPoint = "/api/email/";
        response = postRequest(endPoint, code, randomDataBodyForCreateEmail());
        return response;
    }

    public Response getEmail(Integer code, int emailId) {
        String endPoint = "/api/email/{id}";
        response = getRequestWithParam(endPoint, code, emailId);
        return response;
    }

    //   public Response getEmailWithParam(Integer code, int contactId) {
//        String endPoint = "/api/email/{contactId}/all";
    //       response = getRequestWithParam(endPoint, code, contactId);
    //       return response;
    //  }


    public void editExistingEmail(Integer code, Integer emailId) {
        String endPoint = "/api/email/";
        putRequest(endPoint, code, randomDataBodyForEditEmail(emailId));

    }

    public void deleteExistingEmail(Integer code, int emailId) {
        String endPoint = "/api/email/{id}";
        deleteRequest(endPoint, code, emailId);

    }


}
