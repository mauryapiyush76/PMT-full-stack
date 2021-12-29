package io.piyush.pmt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.piyush.pmt.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	public Project findByProjectIdentifier(String projectIdentifier);

	@Override
	Iterable<Project> findAll();

}
