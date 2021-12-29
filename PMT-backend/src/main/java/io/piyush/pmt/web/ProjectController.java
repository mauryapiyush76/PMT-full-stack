package io.piyush.pmt.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.piyush.pmt.domain.Project;
import io.piyush.pmt.services.ProjectService;
import io.piyush.pmt.services.ValidationMapService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ValidationMapService validationMapService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = validationMapService.getValidationErrorsMap(result);
		if (errorMap != null)
			return errorMap;
		Project project2 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project2, HttpStatus.CREATED);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getByProjectId(@PathVariable String projectId) {
		Project project = projectService.getByProjectId(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.getAllProjects();
	}
}
