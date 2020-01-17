package root.demo.handlers;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AddArea implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

        System.out.println("AddArea");
        List<String> naucneOblasti = (List<String>) delegateExecution.getVariable("naucneOblasti");
        System.out.println(delegateExecution.getVariable("brojacOblasti"));
        if(naucneOblasti == null)
            naucneOblasti = new ArrayList<>();

        System.out.println("Element:" + (String) delegateExecution.getVariable("element"));
        naucneOblasti.add((String) delegateExecution.getVariable("element"));
        delegateExecution.setVariable("naucneOblasti",naucneOblasti);

    }
}
