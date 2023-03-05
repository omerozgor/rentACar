package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mapper.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService{
	
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;
	
	

	@Override
	public List<GetAllBrandsResponse> getAll() {
		// İş kodları
		
		List<Brand> brands = brandRepository.findAll();
		
		List<GetAllBrandsResponse> responses = brands.stream().map(brand -> modelMapperService.responseMapper()
				.map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		return responses;
	}


	@Override
	public void add(CreateBrandRequest createBrandRequest) {

		brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		
		Brand brand = modelMapperService.requestMapper()
				.map(createBrandRequest, Brand.class);
		
		brandRepository.save(brand);
		
	}


	@Override
	public GetByIdBrandResponse getById(int id) {
		
		Brand brand = brandRepository.findById(id).orElseThrow();
		GetByIdBrandResponse response = modelMapperService.requestMapper()
				.map(brand, GetByIdBrandResponse.class);
		return response;
	}


	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = modelMapperService.requestMapper()
				.map(updateBrandRequest, Brand.class);
		
		brandRepository.save(brand);
		
	}


	@Override
	public void delete(int id) {
		brandRepository.deleteById(id);
		
	}

	
}
