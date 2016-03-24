package spring.webjson.client.dao;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class JsonFilter {

	// propriétés
	private String name;
	private SimpleBeanPropertyFilter filter;

	// constructeurs
	public JsonFilter() {

	}

	public JsonFilter(String name, String... fields) {
		this.name = name;
		if (fields == null || fields.length == 0) {
			filter = SimpleBeanPropertyFilter.serializeAllExcept();
		} else {
			filter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
		}
	}

	// getters et setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SimpleBeanPropertyFilter getFilter() {
		return filter;
	}

	public void setFilter(SimpleBeanPropertyFilter filter) {
		this.filter = filter;
	}
}
