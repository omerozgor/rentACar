package kodlama.io.rentACar.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateBrandRequest {
	
	private int id;
	private String name;

}
