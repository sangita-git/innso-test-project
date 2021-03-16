package innso.test.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

/* The object of this class represents a single ClientCase */
@Entity
@Table(name = "clientcase")
@Data
public class ClientCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1973362989887614394L;

	@Id
	@SequenceGenerator(initialValue = 1001, name = "cc_seq", sequenceName = "cc_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cc_seq")
	@Column(name = "id")
	private long id; //primary-key is auto generated

	@Column(nullable = false, length = 100)
	private String clientName;

	@Column(nullable = false, unique = true)
	@UpdateTimestamp
	private LocalDateTime creationTime; //creation time of the client-case is auto generated
	
	@Column(length = 100)
	private String reference;

	@OneToMany
	@JoinColumn(name = "messageid")
	private List<Message> message; //each client-case can have multiple messages linked to it
	
}