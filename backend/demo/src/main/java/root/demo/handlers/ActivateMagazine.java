package root.demo.handlers;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

@Service
public class ActivateMagazine implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {

    }
}
