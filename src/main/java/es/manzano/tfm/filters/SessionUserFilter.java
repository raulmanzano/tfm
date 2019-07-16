package es.manzano.tfm.filters;

import es.manzano.tfm.Info;
import es.manzano.tfm.SecurityFilter;
import es.manzano.tfm.exceptions.SessionUserFilterException;

public class SessionUserFilter  implements Filter {

	public void filter(Info previous, Info current) throws SessionUserFilterException {
		
		if (current.getSessionId()==null) throw new SessionUserFilterException("SessionId nulo");
		if (current.getUserId()==null) throw new SessionUserFilterException("UserId nulo");
			
		if (!current.getSessionId().equalsIgnoreCase(previous.getSessionId())) throw new SessionUserFilterException("SessionId diferentes");
		if (previous.getUserId()!=SecurityFilter.NO_USER)
			if (!current.getUserId().equalsIgnoreCase(previous.getUserId())) throw new SessionUserFilterException("UserId diferentes");
		if (!current.getAddr().equalsIgnoreCase(previous.getAddr())) throw new SessionUserFilterException("Addr diferentes");
		
	}

}
