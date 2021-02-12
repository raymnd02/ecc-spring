package com.exist.utils;
import java.util.*;
import java.io.*;
import com.exist.model.Person;
public class Utils {
	public static List<Person> getFixList(List<Person> personsToFix) {
		personsToFix.stream()
			.forEach(nullperson -> {
				nullperson.getRole().stream()
					.forEach(eachRole -> {
						List<Person> persons = new ArrayList();
						eachRole.setPersonRole(persons);
					});
			});
		return personsToFix;
	}
}
