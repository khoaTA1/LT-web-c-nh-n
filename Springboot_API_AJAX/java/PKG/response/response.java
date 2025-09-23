package PKG.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class response {
	private Boolean status;
	private String msg;
	private Object body;
}
