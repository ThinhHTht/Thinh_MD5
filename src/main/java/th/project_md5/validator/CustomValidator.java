package th.project_md5.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import th.project_md5.model.dto.request.RegisterUser;
import th.project_md5.repository.UserRepository;

@Component
public class UserValidate implements Validator {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterUser registerUser = (RegisterUser) target;
        if(userRepository.existsByUsername(registerUser.getUsername())){
            errors.rejectValue("username", "username_err", "This username has already existed");
        }
        if(userRepository.existsByPhone(registerUser.getPhone())){
            errors.rejectValue("phone", "phone_existed", "This phone number has already existed");
        }
    }
}
