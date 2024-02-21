package yun.jung.kim.Host_Api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "USER_ACCOUNT",
        indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @Column(length = 50)
    private String userId;

    @Setter @Column(nullable = false) private String userPassword;

    @Setter @Column(length = 100) private String email;

    private UserAccount(String userId, String userPassword, String email) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
    }

    public static UserAccount of(String userId, String userPassword, String email) {
        return UserAccount.of(userId, userPassword, email, null);
    }

    public static UserAccount of(String userId, String userPassword, String email, String createdAt) {
        return UserAccount.of(userId, userPassword, email, createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return userId == userAccount.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }
}
