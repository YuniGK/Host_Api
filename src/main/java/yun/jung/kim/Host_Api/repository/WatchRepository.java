package yun.jung.kim.Host_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.Watch;

@RepositoryRestResource
public interface WatchRepository extends JpaRepository<Watch, Long> {
}
