package root.demo.controller;


import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import root.demo.dtos.FormFieldsDto;
import root.demo.dtos.FormSubmissionDto;
import root.demo.dtos.LoginDto;
import root.demo.dtos.RegisterUserDto;
import root.demo.services.UserService;
import root.demo.utils.TokenUtils;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/magazine")
public class MagazineController {
    @Autowired
    IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    FormService formService;

    @Autowired
    UserService userService;

    @PreAuthorize("@securityService.hasProtectedAccess('ROLE_USER')")
    @GetMapping(path = "/get", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {

        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Proces_Dodavanja_Casopisa");
        System.out.println("ProcessInstance" + pi.getId());
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
        for(FormField fp : properties) {
            System.out.println(fp.getId() + fp.getType());
        }
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }
}

