package root.demo.services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import root.demo.dtos.FormSubmissionDto;
import root.demo.model.RoleEnum;
import root.demo.model.User;
import root.demo.repository.UserRepository;

import java.util.List;

@Service
public class ClientRegistration implements JavaDelegate {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcript;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //get podatke i sacuvaj :D
        System.out.println("Registracija");
        User user = new User();
        List<FormSubmissionDto> formFields = (List<FormSubmissionDto>) delegateExecution.getVariable("registration");
        for (FormSubmissionDto field: formFields) {
            if(field.getFieldId().equals("username"))
                user.setUsername(field.getFieldValue());
            if(field.getFieldId().equals("password"))
                user.setPassword(bcript.encode(field.getFieldValue()));
            if(field.getFieldId().equals("email"))
                user.setEmail(field.getFieldValue());
            if(field.getFieldId().equals("grad"))
                user.setCity(field.getFieldValue());
            if(field.getFieldId().equals("drzava"))
                user.setCountry(field.getFieldValue());
            if(field.getFieldId().equals("recezent")) {
                user.setReviewer(Boolean.parseBoolean(field.getFieldValue()));
                user.setRoleEnum(RoleEnum.ROLE_REVIEWER);
            }
            else user.setRoleEnum(RoleEnum.ROLE_USER);
            if(field.getFieldId().equals("titula"))
                user.setTitle(field.getFieldValue());
        }
        userRepository.save(user);
    }
}
