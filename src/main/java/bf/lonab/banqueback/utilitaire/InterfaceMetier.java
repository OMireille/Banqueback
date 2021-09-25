package bf.lonab.banqueback.utilitaire;

import java.util.List;

import bf.lonab.banqueback.exceptions.InvalideBanquebackException;

public interface InterfaceMetier<T,U> {
	public T creer(T entite) throws InvalideBanquebackException;
	public T modifier(T entite);
	public boolean supprimer(U identifiant);
	public List<T> lister();
	public T trouver(U identifiant);
	
}
