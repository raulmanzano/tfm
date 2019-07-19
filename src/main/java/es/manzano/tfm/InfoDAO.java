package es.manzano.tfm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class InfoDAO {

	private HashMap<String, Info> store = new LinkedHashMap<String, Info>();

	public Info get(String sessionId) {
		return store.get(sessionId);
	}

	public void set(Info currentInfo) {
		store.put(currentInfo.getSessionId(), currentInfo);
	}

	public void reset(Info currentInfo) {
		Info info = store.get(currentInfo.getSessionId());
		if (info != null)
			info.reset();
	}

	public Info get(Info info) {
		return get(info.getSessionId());
	}

	public void reset() {

		this.store = new LinkedHashMap<String, Info>();
	}

	public List<Info> findUser(String userId) {
		List<Info> salida = new ArrayList<Info>();
		for (Info info : store.values()) {
			if (userId.equalsIgnoreCase(info.getUserId()))
				salida.add(info);
		}
		return salida;
	}
}
