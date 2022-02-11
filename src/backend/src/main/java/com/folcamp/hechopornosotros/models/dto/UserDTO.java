package com.folcamp.hechopornosotros.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.firebase.auth.UserRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("photo_url")
    private String photoURL;
    @JsonProperty("provider_id")
    private String providerId;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("custom_claims")
    private Map<String, Object> customClaims;


    public UserDTO(UserRecord userRecord){
        displayName = userRecord.getDisplayName();
        email = userRecord.getEmail();
        phoneNumber = userRecord.getPhoneNumber();
        photoURL = userRecord.getPhotoUrl();
        providerId = userRecord.getProviderId();
        uid = userRecord.getUid();
        customClaims = userRecord.getCustomClaims();
    }
}



