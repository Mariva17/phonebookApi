package api.phone;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.phone.PhoneDto;

public class PhoneApi extends ApiBase {
    Response response;

    PhoneDto dto;

    Faker faker = new Faker();

    public PhoneDto randomDataBodyForCreatePhone(Integer contactId) {
        dto = new PhoneDto();
        dto.setCountryCode("+49");
        dto.setPhoneNumber("1752777990");
        dto.setContactId(contactId);
        return dto;
    }

    public PhoneDto randomDataBodyForEditPhone(Integer phoneId, Integer contactId) {
        dto = new PhoneDto();
        dto.setId(phoneId);
        dto.setCountryCode("+49");
        dto.setPhoneNumber("1751115012");
        dto.setContactId(contactId);
        return dto;
    }

    public Response createPhone(Integer code, Integer contactId) {
        String endPoint = "/api/phone/";
        response = postRequest(endPoint, code, randomDataBodyForCreatePhone(contactId));
        return response;
    }

    public Response getAllPhones(Integer code, Integer contactId) {
        String endPoint = "/api/phone/{id}/all";
        response = getRequestWithParam(endPoint, code, contactId);
        return response;
    }

    public Response getPhone(Integer code, int phoneId) {
        String endPoint = "/api/phone/{id}";
        response = getRequestWithParam(endPoint, code, phoneId);
        return response;
    }


    public void editExistingPhone(Integer code, Integer phoneId, Integer contactId) {
        String endPoint = "/api/phone/";
        putRequest(endPoint, code, randomDataBodyForEditPhone(phoneId, contactId));

    }

    public void deleteExistingPhone(Integer code, int phoneId) {
        String endPoint = "/api/phone/{id}";
        deleteRequest(endPoint, code, phoneId);

    }
}
