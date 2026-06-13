package com.hammad.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@Positive(message = "Roll number must be positive")
	private int rollNo;

	@NotBlank(message = "Student name is required")
	@Size(max = 100, message = "Student name must not exceed 100 characters")
	private String name;

	@Min(value = 0, message = "Marks cannot be negative")
	@Max(value = 100, message = "Marks cannot exceed 100")
	private int marks;
}


