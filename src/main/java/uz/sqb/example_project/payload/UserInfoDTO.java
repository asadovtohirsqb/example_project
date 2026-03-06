package uz.sqb.example_project.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDTO {

    private Long id;
    private String pnfl;
    private String phone;
    private String firstname;
    private String lastname;
    private String birthday;
    private String clientCode;
    private String lang;
    private String iabsId;

}
