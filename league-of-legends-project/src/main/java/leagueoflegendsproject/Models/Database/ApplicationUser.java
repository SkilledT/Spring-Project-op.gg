package leagueoflegendsproject.Models.Database;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@AllArgsConstructor
@Data
@Entity
@Table(name = "application_user")
public class ApplicationUser implements UserDetails {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "isaccountnonexpired")
    @Type(type = "numeric_boolean")
    private boolean isAccountNonExpired;
    @Column(name = "isaccountnonlocked")
    @Type(type = "numeric_boolean")
    private boolean isAccountNonLocked;
    @Column(name = "iscredentialsnonexpired")
    @Type(type = "numeric_boolean")
    private boolean isCredentialsNonExpired;
    @Column(name = "isenabled")
    @Type(type = "numeric_boolean")
    private boolean isEnabled;


    public ApplicationUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
