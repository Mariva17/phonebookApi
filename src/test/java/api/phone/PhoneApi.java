package api.phone;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.phone.PhoneDto;

public class PhoneApi extends ApiBase {
    Response response;

    PhoneDto dto;

    Faker faker = new Faker();

    public PhoneDto randomDataBodyForCreatePhone() {
        dto = new PhoneDto();
        dto.setCountryCode("+49");
        dto.setPhoneNumber("175222222");
        dto.setContactId(4911);
        return dto;
    }

    public PhoneDto randomDataBodyForEditPhone(Integer phoneId) {
        dto = new PhoneDto();
        dto.setId(phoneId);
        dto.setCountryCode("+49");
        dto.setPhoneNumber("175111555");
        dto.setContactId(4911);
        return dto;
    }

    public Response createPhone(Integer code) {
        String endPoint = "/api/phone/";
        response = postRequest(endPoint, code, randomDataBodyForCreatePhone());
        return response;
    }

    public Response getAllPhones(Integer code) {
        String endPoint = "/api/phone/{id}/all";
        response = getRequestWithParam(endPoint, code, 4911);
        return response;
    }

    public Response getPhone(Integer code, int phoneId) {
        String endPoint = "/api/phone/{id}";
        response = getRequestWithParam(endPoint, code, phoneId);
        return response;
    }


    public void editExistingPhone(Integer code, Integer phoneId) {
        String endPoint = "/api/phone/";
        putRequest(endPoint, code, randomDataBodyForEditPhone(phoneId));

    }

    public void deleteExistingPhone(Integer code, int phoneId) {
        String endPoint = "/api/phone/{id}";
        deleteRequest(endPoint, code, phoneId);

    }
}
