package root.demo.handlers;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class GetEditors implements TaskListener {

    @Autowired
    FormService formService;

    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("GetEditors");
        TaskFormData tfd = formService.getTaskFormData(delegateTask.getId());
        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getLabel());
            if (fp.getLabel().equals("Urednik naucnih oblasti")) {
                EnumFormType enumFormType = (EnumFormType) fp.getType();
                Map<String, String> values = enumFormType.getValues();
                //Iz baze treba vuci :D
                values.put("1", "maki");
                values.put("2", "lolisha");
            }
        }
    }
 }

