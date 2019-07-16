package es.manzano.tfm.filters;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;
import es.manzano.tfm.exceptions.SourceTargetFilterException;

public class PreviousCurrentFilter implements Filter {

	public void filter(Info previous, Info current) throws FilterException {
		
		if (!current.getPrevious().equalsIgnoreCase(previous.getCurrent())) throw new SourceTargetFilterException("Previous diferente");
		
	}


}
