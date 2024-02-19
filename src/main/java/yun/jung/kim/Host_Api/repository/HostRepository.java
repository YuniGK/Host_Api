package yun.jung.kim.Host_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yun.jung.kim.Host_Api.domain.Host;

@RepositoryRestResource
public interface HostRepository extends JpaRepository<Host, Long> {
}
