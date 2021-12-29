package io.piyush.pmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.piyush.pmt.domain.Project;
import io.piyush.pmt.exceptions.ProjectIdException;
import io.piyush.pmt.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("PROJECT ID '" + project.getProjectIdentifier() + "' already exists");
		}
	}

	public Project getByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException(projectId + " does not exist");
		}
		return project;
	}

	public Iterable<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Cannot delete unexisting project with PROJECT ID '" + projectId + "'");
		}
		projectRepository.delete(project);
	}

	public Project updateProject(Project updatedProject, String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException(
					"Cannot update project because no project exists with PROJECT ID '" + projectId + "'");
		}
		if (updatedProject.getDescription() != null) {
			project.setDescription(updatedProject.getDescription());
		}
		if (updatedProject.getStart_date() != null) {
			project.setStart_date(updatedProject.getStart_date());
		}
		if (updatedProject.getEnd_date() != null) {
			project.setEnd_date(updatedProject.getEnd_date());
		}
		if (updatedProject.getProjectName() != null) {
			project.setProjectName(updatedProject.getProjectName());
		}
		if (updatedProject.getId() != null) {
			project.setId(updatedProject.getId());
		}

		return project;
	}
}
