package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AddressController {
	
	@Autowired
	private AddressRepo addressRepo;
	
	
	@PostMapping("/api/addresses")
	public ResponseEntity<Address>saveAddress(@RequestBody Address address)
	{
		Address a=addressRepo.save(address);
		return new ResponseEntity<Address>(a,HttpStatus.CREATED);
	}
	
	@GetMapping("/api/addresses")
	public ResponseEntity<List<Address>>getAllAddresses()
	{
		List<Address>ad=addressRepo.findAll();
		return new ResponseEntity<List<Address>>(ad,HttpStatus.OK);
	}
	
	@GetMapping("/api/addresses/{id}")
	public ResponseEntity<Address>getAddressById(@PathVariable int id)
	{
		Optional<Address>ads=addressRepo.findById(id);
		if(ads.isPresent())
		{
			return new ResponseEntity<Address>(ads.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/addresses/{id}")
	public ResponseEntity<Address>updateAddress(@PathVariable int id,@RequestBody Address updatedAddress)
	{
		Optional<Address>ads=addressRepo.findById(id);
		
		if(ads.isPresent())
		{
			Address address=ads.get();
			address.setCity(updatedAddress.getCity());
			address.setZipCode(updatedAddress.getZipCode());
			
			Address adss=addressRepo.save(address);
			return new ResponseEntity<Address>(adss,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
	}

	
	@DeleteMapping("/api/addresses/{id}")
	public ResponseEntity<Void>deleteAddress(@PathVariable int id)
	{
		Optional<Address> address=addressRepo.findById(id);
		
		if(address.isPresent())
		{
			addressRepo.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
