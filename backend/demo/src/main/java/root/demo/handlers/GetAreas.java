package root.demo.handlers;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.FormType;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.impl.form.type.FormTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GetAreas implements TaskListener {

    @Autowired
    FormService formService;

    @Override
    public void notify(DelegateTask delegateTask) {

        System.out.println("GetAreas");
        TaskFormData tfd = formService.getTaskFormData(delegateTask.getId());
        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getLabel());
            if (fp.getLabel().equals("Biranje oblasti")) {
                EnumFormType enumFormType = (EnumFormType) fp.getType();
                Map<String, String> values = enumFormType.getValues();
                //Iz baze treba vuci :D
                values.put("1", "naucnaOblast1");
                values.put("2", "naucnaOblast2");
            }
        }
    }
}
