package bf.lonab.banqueback.utilitaire;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class GestionExceptions  {
	public static List<String> getErrorForException(Exception e){
		Throwable cause = e;
		List<String> erreurs = new ArrayList<>();
		while (cause!=null) {
			String message = cause.getMessage();
			if (message!=null) {
				if (message.trim().length()!=0) {
					erreurs.add(message.trim());
				}
	     	}
			cause=cause.getCause();
			}
		return erreurs;
	}
}
