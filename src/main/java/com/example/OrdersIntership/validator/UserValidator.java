package com.example.OrdersIntership.validator;

import com.example.OrdersIntership.dto.UserDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserDto userToValidate = (UserDto) target;

            if (StringUtils.isBlank(userToValidate.getEmail()) || StringUtils.isBlank(userToValidate.getPassword())) {
                errors.rejectValue("password", "400", "Please fill out the required field");
            }

            if (!validEmail(userToValidate.getEmail())) {
                errors.rejectValue("email", "400", "Please make sure you are using a valid email or password");
            }

            if (!validEmail(userToValidate.getPassword())) {
                errors.rejectValue("password", "400", "Please make sure you are using a valid email or password");
            }

    }


    public boolean validEmail(String email){

        boolean flag = false;

        if(email.contains("@")){
            flag = true;
        }

        if(email.contains(".")){
            flag = true;
        }

        if(email.startsWith("@")){
            flag = false;
        }

        if(email.endsWith("@")){
            flag = false;
        }

        if(email.startsWith(".")){
            flag = false;
        }

        if(email.endsWith(".")){
            flag = false;
        }

        if(email.length() > 100){
            flag = false;
        }

        return flag;
    }


    public boolean validPassword(String password){

        if (password.length() < 5) {
            return false;
        }

        if (password.length() > 20) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        String specialChars = "~.\"(),:;<>@[]!#$%&'*+-/=?^_`{|}";

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (specialChars.contains(String.valueOf(ch))) {
                hasSpecialChar = true;
            }
        }

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;

    }

}
