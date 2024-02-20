package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditingFields {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;//등록 시간 or 사건 발생 일시
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;//등록자 or 사건 발생 주체의 사원

    @LastModifiedDate
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime modifiedAt;//수정 시간 or 사건 수정 일시
    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy;//수정자 or 사건 수정 주체의 사원
}
