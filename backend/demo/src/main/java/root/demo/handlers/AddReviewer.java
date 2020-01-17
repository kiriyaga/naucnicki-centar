package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddReviewer implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

        System.out.println("AddReviewer");
        List<String> listaRecezenata = (List<String>) delegateExecution.getVariable("listaRecezenata");
        if(listaRecezenata == null)
            listaRecezenata = new ArrayList<>();

        System.out.println("Element:" + (String) delegateExecution.getVariable("element2"));
        listaRecezenata.add((String) delegateExecution.getVariable("element2"));
        delegateExecution.setVariable("listaRecezenata",listaRecezenata);

    }
}
