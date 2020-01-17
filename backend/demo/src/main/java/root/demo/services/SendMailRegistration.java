package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.extension.mail.dto.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.demo.utils.TokenUtils;

@Service
public class SendMailRegistration implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Send mail");
        System.out.println((String) delegateExecution.getVariable("username"));
        String token = tokenUtils.generateToken((String) delegateExecution.getVariable("username"),delegateExecution.getProcessInstanceId());
        mailService.sendRegistrationActivation((String) delegateExecution.getVariable("email"),token);
    }
}
