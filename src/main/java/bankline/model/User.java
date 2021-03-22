package bankline.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bankline.form.UserForm;


@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	@NotNull @NotEmpty @Size(max = 11)
	private String cpf;
	
	@Column(unique = true, nullable = false, length = 20)
	private String login;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	public User() {

	}
	
	public User(UserForm userForm) {
		this.cpf = userForm.getCpf();
		this.login = userForm.getLogin();
		this.name = userForm.getName();
		this.password = userForm.getPassword();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Account converter(User user) {
		return new Account(user);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}
	@Override
	public String getUsername() {
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
