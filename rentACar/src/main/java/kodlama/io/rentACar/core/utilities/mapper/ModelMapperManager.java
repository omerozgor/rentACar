package kodlama.io.rentACar.core.utilities.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ModelMapperManager implements ModelMapperService{
	
	private ModelMapper mapper;

	@Override
	public ModelMapper responseMapper() {
		mapper.getConfiguration()
			.setAmbiguityIgnored(true)
			.setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper;
	}

	@Override
	public ModelMapper requestMapper() {
		mapper.getConfiguration()
			.setAmbiguityIgnored(true)
			.setMatchingStrategy(MatchingStrategies.STANDARD);
		return mapper;
	}

}
