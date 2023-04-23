package api.contact;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.contact.ContactDto;

public class ContactApi extends ApiBase {
    Response response;

    ContactDto dto;

    Faker faker = new Faker();

    public ContactDto randomDataBodyForCreateContact() {
        dto = new ContactDto();
        dto.setFirstName(faker.internet().uuid());
        dto.setLastName(faker.internet().uuid());
        dto.setDescription(faker.internet().uuid());
        return dto;
    }

    public ContactDto randomDataBodyForEditContact(Integer contactId) {
        dto = new ContactDto();
        dto.setId(contactId);
        dto.setFirstName("Rita");
        dto.setLastName("Baghi");
        dto.setDescription("edited");
        return dto;
    }

    public Response createContact(Integer code) {
        String endPoint = "/api/contact/";
        response = postRequest(endPoint, code, randomDataBodyForCreateContact());
        return response;
    }

    public void editExistingContact(Integer code, Integer contactId) {
        String endPoint = "/api/contact/";
        putRequest(endPoint, code, randomDataBodyForEditContact(contactId));

    }

    public void deleteExistingContact(Integer code, int contactId) {
        String endPoint = "/api/contact/{id}";
        deleteRequest(endPoint, code, contactId);
    }

    public Response getContact(Integer code, int contactId) {
        String endPoint = "/api/contact/{id}";
        response = getRequestWithParam(endPoint, code, contactId);
        return response;
    }

}
