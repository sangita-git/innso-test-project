package innso.test.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/* 
 * The object of this class represents a single Channel 
 * Values into the specific table is uploaded 
 * using src/main/resource/data.sql
 * */
@Entity
@Table(name = "channel")
@Data
public class Channel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2872014504279542826L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id; //primary-key is auto generated
	
	@Column(nullable = false, unique = true)
	private String source;
	
}
