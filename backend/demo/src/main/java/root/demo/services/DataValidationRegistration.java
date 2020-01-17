package root.demo.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class DataValidationRegistration implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        //Neke validacije
        System.out.println("DataValidationRegister");
        delegateExecution.setVariable("validacija ",true);
    }
}
