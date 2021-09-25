package bf.lonab.banqueback.utilitaire;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Reponse<T> {
	private int statut;
	private List<String> msg;
	private T body;
}
