package com.example.myapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {
	
	@NotEmpty
	@Column(name = "first_name", nullable = false)
	private String first;
	
	@NotEmpty
	@Column(name = "last_name", nullable = false)
	private String last;
	
	@Builder
	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	
}
