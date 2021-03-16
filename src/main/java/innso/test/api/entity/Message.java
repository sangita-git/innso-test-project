package innso.test.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/* The object of this class represents a single Message */
@Entity
@Table(name = "message")
@Data
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3834352349588793585L;

	@Id
	@SequenceGenerator(initialValue = 101, name = "msg_seq", sequenceName = "msg_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
	@Column(name = "id", insertable = false)
	private long id; //primary-key auto generated

	@Column(nullable = false, length = 100)
	private String authorName;


	@Column(nullable = false, unique = true)
	@UpdateTimestamp
	private LocalDateTime creationTime; //creation time of the message is auto generated

	@Column(nullable = false)
	private String msgContent;

	@OneToOne
	@JoinColumn(name = "channelid")
	private Channel channel; //each message has been received from a single channel

}
