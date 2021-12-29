package io.piyush.pmt.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public Iterable<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteByProjectId(@PathVariable String projectId) {
		projectService.deleteByProjectId(projectId);
		return new ResponseEntity<String>("Project with PROJECT ID '" + projectId + "' deleted", HttpStatus.OK);
	}

	@PutMapping("/{projectId}")
	public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, @PathVariable String projectId,
			BindingResult result) {
		ResponseEntity<?> errorMap = validationMapService.getValidationErrorsMap(result);
		if (errorMap != null)
			return errorMap;
		Project project2 = projectService.updateProject(project, projectId);
		return new ResponseEntity<Project>(project2, HttpStatus.OK);
	}
}
