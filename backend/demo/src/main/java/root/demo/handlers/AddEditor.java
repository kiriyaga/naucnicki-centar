package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddEditor implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

        System.out.println("AddEditor");
        List<String> listaEditora = (List<String>) delegateExecution.getVariable("listaEditora");
        if(listaEditora == null)
            listaEditora = new ArrayList<>();

        System.out.println("Element:" + (String) delegateExecution.getVariable("element3"));
        listaEditora.add((String) delegateExecution.getVariable("element3"));
        delegateExecution.setVariable("listaEditora",listaEditora);

    }
}
