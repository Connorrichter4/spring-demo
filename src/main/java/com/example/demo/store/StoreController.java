package com.example.demo.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

	@Autowired
	ProductEntityCrudRepository productEntityCrudRepository;

	@GetMapping(path = "/contact")
	String contact() {

		return "123-456-7890";
	}
	
	@GetMapping(path = "/createProduct", produces = "text/html")
	String showProductForm() {
		
		String output = "<form action='' method='POST'>";
		output += "Name: <input name='name' type='text' /><br />";
		output += "Price: <input name='price' type='text' /><br />";
		output += "<input type='submit' value='submit' />";
		output += "</form>";
		
		return output;
	}
	
	@PostMapping(path = "/createProduct")
	void createProduct(@ModelAttribute ProductEntity product) {
		if (product == null || product.getName() == null) {
			throw new RuntimeException("Name required");
		}
		if (product.getPrice() < 0) {
			throw new RuntimeException("Price required");
		}
		productEntityCrudRepository.save(product);
	}

	@GetMapping(path = "/home")
	String home() {

//		ProductEntity merlot = new ProductEntity();
//		merlot.setName("Best merlot ever");
//		merlot.setPrice(33.88F);
//		productEntityCrudRepository.save(merlot);
//		
//		ProductEntity cab = new ProductEntity();
//		cab.setName("Only okay cab");
//		cab.setPrice(42.54F);
//		productEntityCrudRepository.save(cab);
		
		Iterable<ProductEntity> products = productEntityCrudRepository.findAll();
        products.forEach((p) -> {
            System.out.println(p.getName());
        });

		return "Hello world";
	}

}
