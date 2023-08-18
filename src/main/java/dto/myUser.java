package dto;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class myUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;

//	@Column(unique=true)
	String email;
	long mobile;
	String gender;
	LocalDate dod;
	String[] language;
	String address;
	String password;
	@OneToMany
    List<Task>list;
	
	
	
}
