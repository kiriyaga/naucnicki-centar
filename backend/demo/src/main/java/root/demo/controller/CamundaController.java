package root.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import root.demo.dtos.FormFieldsDto;
import root.demo.dtos.FormSubmissionDto;
import root.demo.dtos.LoginDto;
import root.demo.dtos.RegisterUserDto;
import root.demo.model.RoleEnum;
import root.demo.model.User;
import root.demo.repository.UserRepository;
import root.demo.services.UserService;
import root.demo.utils.TokenUtils;

@Controller
@RequestMapping("/register")
public class CamundaController {
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

	@Autowired
	UserRepository userRepository;


	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@RequestBody LoginDto user) {
		RegisterUserDto userReg = userService.userLogin(user.getUsername(), user.getPassword());
		if(userReg == null)
			return new ResponseEntity<>("Wrong credinitals!", HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(userReg,HttpStatus.OK);
	}
	
	@GetMapping(path = "/get", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Proces_registracije");
		System.out.println("ProcessInstance" + pi.getId());
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

	@GetMapping(path = "/get/{username}", produces = "application/json")
	public @ResponseBody FormFieldsDto getTasksForAdmin(@PathVariable String username) {

		if(taskService.createTaskQuery().taskAssignee(username).list().isEmpty())
			return  null;
		Task task = taskService.createTaskQuery().taskAssignee(username).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}

		return new FormFieldsDto(task.getId(), task.getProcessInstanceId(), properties);
	}

	@GetMapping(path = "/validate/{token}", produces = "application/json")
    public @ResponseBody ResponseEntity get(@PathVariable String token){

		System.out.println("Token " + token);
		if (!tokenUtils.validateToken(token))
			return new ResponseEntity("Cant validate registration!",HttpStatus.OK);

		String processInstanceId = tokenUtils.getProcessIdFromToken(token);
		System.out.println("Process instance " + processInstanceId);
		MessageCorrelationResult results = runtimeService.createMessageCorrelation("PotvrdaMejla")
				.processInstanceId(processInstanceId).correlateWithResult();

		return new ResponseEntity("Successful mail confirmation!",HttpStatus.OK);
    }
	
	@PostMapping(path = "/post/{taskId}", produces = "application/json")
    public @ResponseBody FormFieldsDto post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		if(task.getName().equals("Osnovni podaci korisnika"))
			runtimeService.setVariable(processInstanceId, "registration", dto);
		else if(task.getName().equals("Osnovni podaci o casopisu"))
			runtimeService.setVariable(processInstanceId, "magazine", dto);

		formService.submitTaskForm(taskId, map);
		if(!taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list().isEmpty()){
		Task taskNew = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list().get(0);
			TaskFormData tfd = formService.getTaskFormData(taskNew.getId());
			List<FormField> properties = tfd.getFormFields();
			for(FormField fp : properties) {
				System.out.println(fp.getId() + fp.getType());
			}
			return new FormFieldsDto(taskNew.getId(), taskNew.getProcessInstanceId() , properties);
		}
		return new FormFieldsDto(task.getId(), task.getProcessInstanceId() , null);
    }

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addAdmin")
	public ResponseEntity<?> addAdmin() {

		User user = new User();
		user.setRoleEnum(RoleEnum.ROLE_ADMIN);
		user.setUsername("demo");
		user.setPassword("demo");
		user.setEmail("makiarambasic@gmail.com");
		user.setActive(true);

		return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
	}

}
