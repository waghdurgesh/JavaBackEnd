package com.app.pojos;

import javax.persistence.*;
import lombok.*;

@MappedSuperclass // to avoid table generation

@Getter
@Setter
@ToString
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
}
