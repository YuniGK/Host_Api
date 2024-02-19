package yun.jung.kim.Host_Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yun.jung.kim.Host_Api.domain.Host;

public interface HostRepository extends JpaRepository<Host, Long> {
}
