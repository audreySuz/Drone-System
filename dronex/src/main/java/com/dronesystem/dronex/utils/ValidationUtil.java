package com.dronesystem.dronex.utils;

import com.dronesystem.dronex.entities.Medication;
import com.dronesystem.dronex.exceptions.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ValidationUtil {

    private final ObjectMapper mapper;

    public Medication getTicketRequest(String mainRequest) throws Exception {
        Medication request = null;
        try {
            request = mapper.readValue(mainRequest, Medication.class);
//String errors = validateFields(request);
//            if (!errors.trim().isEmpty()) {
//                throw new Exception("field validations failed with errors : " + errors);
//            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Exception("Could not read your json request with error : " + e.getMessage()
                    + ":::: \n with request body : " + mainRequest);
        }
        return request;
    }

    public boolean validateMedicationName(String name) {
        return name.matches("[a-zA-Z_0-9_'-]*");
    }

    public boolean validateCode(String name) {
        return name.matches("[A-Z_0-9_']*");
    }

    public String formatString(String input){
    input=input.toUpperCase();
    input= input.trim();
    input=input.replace(" ", "");
    return input;
    }
    
    public void validateBase64Image(String image) {
        String allowedType = "png jpg jepg gif";

        String prefix = "data:image/";
        if (image.startsWith(prefix)) { //true
            image = image.replace(prefix, ""); //png;base64,iVBORw0KGgoAAAANSUhEUgAAAqwAAAFeCAYAA
            String[] split = image.split(";"); //["png",  "base64,iVBORw0KGgoAAAANSUhEUgAAAqwAAAFeCAYAA"]
            String imgeType = split[0]; //png
            if (!allowedType.contains(imgeType)) {
                throw new BadRequestException ("Invalid image");
            }
            String base64Data = split[1]; //base64,iVBORw0KGgoAAAANSUhEUgAAAqwAAAFeCAYAA
            base64Data = base64Data.replace("base64,", ""); //iVBORw0KGgoAAAANSUhEUgAAAqwAAAFeCAYAA
            try {
                Base64.getDecoder().decode(base64Data);
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid image");
            }

        } else {
            throw new BadRequestException("Invalid image");
        }
    }
}
