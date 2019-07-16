package es.manzano.tfm.filters;

import java.util.HashSet;
import java.util.Set;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;
import es.manzano.tfm.exceptions.PreviousAllowedFilterException;

public class PreviousAllowedFilter {

	private Set<String> graph = new HashSet<String>();
	
	public PreviousAllowedFilter() {
		this.graph.add("login"+"login");
		this.graph.add("login"+"pagina1");
		this.graph.add("pagina1"+"pagina2");
		this.graph.add("pagina1"+"logout");
		this.graph.add("pagina2"+"pagina3");
		this.graph.add("pagina2"+"logout");
		this.graph.add("pagina3"+"logout");
		this.graph.add("pagina3"+"pagina1");
		this.graph.add("logout"+"login");
		
	}

	public void filter(Info previousInfo, Info currentInfo)  throws FilterException {
		
		if (!(this.graph.contains(currentInfo.getPrevious()+currentInfo.getCurrent()))) throw new PreviousAllowedFilterException("Acceso desde pagina no permitida");

	}

}
