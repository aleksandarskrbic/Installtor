package configurator.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Element implements Serializable {
	
	private static final long serialVersionUID = -5449554805130734155L;

	protected String idGroup = null;
	
	protected String name;
	
	private String id;
	// Grupa i poslednji zauzet id iz grupe
	private static 	Map<String, Integer> ids = new HashMap<String, Integer>();
	
	public Element() {
		idGroup = getClass().getSimpleName();
		generateID();
	}
	
	public Element(String idGroup) {
		this.idGroup = idGroup;
		generateID();
	}
	
	public void generateID() {
		Integer idCount;
		if(ids.containsKey(idGroup)) {
			idCount = ids.get(idGroup);
			++idCount;
		} else
			idCount = 0;

		id = idGroup + "_" + idCount;
		ids.put(idGroup, idCount);
	}
	
	public String getID() {
		return id;
	}
	
	/**
	 * Bezuslovno kopira ID elementa. Koristiti oprezno!
	 * @param id - novi ID
	 */
	public void cloneID(Element e) {
		id = e.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Element) {
			return ((Element) obj).id.equals(id);
		}
		return super.equals(obj);
	}
}
