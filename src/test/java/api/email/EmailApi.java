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
        dto.setContactId(4374);
        return dto;
    }

    public Response createEmail(Integer code) {
        String endPoint = "/api/email/";
        response = postRequest(endPoint, code, randomDataBodyForCreateEmail());
        return response;
    }

    public Response getEmail(Integer code, int contactId) {
        String endPoint = "/api/email/{id}";
        response = getRequestWithParam(endPoint, code, contactId);
        return response;
    }

//    public EmailDto randomDataBodyForEditEmail(Integer emailId) {
//        dto = new EmailDto();
//        dto.setEmailId(emailId);
//        dto.setEmail("m-b@ghhjj");
//        return dto;
//    }
//
//    public Response createContact(Integer code) {
//        String endPoint = "/api/contact/";
//        response = postRequest(endPoint, code, randomDataBodyForCreateContact());
//        return response;
//    }
//
//    public void editExistingContact(Integer code, Integer contactId) {
//        String endPoint = "/api/contact/";
//        putRequest(endPoint, code, randomDataBodyForEditContact(contactId));
//
//    }
//
//    public void deleteExistingContact(Integer code, int contactId) {
//        String endPoint = "/api/contact/{id}";
//        deleteRequest(endPoint, code, contactId);
//    }
//
//    public Response getContact(Integer code, int contactId) {
//        String endPoint = "/api/contact/{id}";
//        response = getRequestWithParam(endPoint, code, contactId);
//        return response;
//    }

}
