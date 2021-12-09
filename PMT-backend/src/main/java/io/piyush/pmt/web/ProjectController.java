package io.piyush.pmt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.piyush.pmt.domain.Project;
import io.piyush.pmt.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

		@Autowired
		private ProjectService projectService;
		
		@PostMapping("")
		public ResponseEntity<Project> createNewProject(@RequestBody Project project){
			Project project2 = projectService.saveOrUpdateProject(project);
			return new ResponseEntity<Project>(project2, HttpStatus.CREATED);
		}
}
