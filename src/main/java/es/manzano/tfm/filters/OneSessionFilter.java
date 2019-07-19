package es.manzano.tfm.filters;

import java.util.List;

import es.manzano.tfm.Info;
import es.manzano.tfm.InfoDAO;
import es.manzano.tfm.exceptions.OneSessionFilterException;

public class OneSessionFilter implements Filter {

	private InfoDAO infoDAO;

	public OneSessionFilter(InfoDAO infoDAO) {
		this.infoDAO = infoDAO;
	}

	public void filter(Info previous, Info current) throws OneSessionFilterException {
		List<Info> infos = infoDAO.findUser(current.getUserId());
		for (Info info : infos) {
			if (info.getSessionId() != current.getSessionId())
				throw new OneSessionFilterException("Existen varios usuarios con diferentes sesiones");
		}
	}

}
