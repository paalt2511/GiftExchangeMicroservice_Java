package com.test.gifts.exchange.controller;

import com.test.gifts.exchange.domain.FamilyMember;
import com.test.gifts.exchange.domain.Response;
import com.test.gifts.exchange.exception.FamilyMemberAlreadyPresentException;
import com.test.gifts.exchange.exception.FamilyMemberNotFoundException;
import com.test.gifts.exchange.services.FamilyMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/members")
public class FamilyMembersController {

	@Autowired
	private FamilyMemberService familyMemberService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FamilyMembersController.class);

	@GetMapping
	public ResponseEntity<List<FamilyMember>> fetchAllFamilyMembers() {
		List<FamilyMember> familyMembers = familyMemberService.listAllFamilyMembers();
		return new ResponseEntity<>(familyMembers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> fetchFamilyMemberById(@PathVariable("id") String id){
		FamilyMember familyMember = null;
		try {
			familyMember = familyMemberService.fetchFamilyMember(id);
		} catch (FamilyMemberNotFoundException e) {
			LOGGER.error("Member does not exists", e);
		}
		return new ResponseEntity<>(familyMember, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Response> addFamilyMember(@RequestBody FamilyMember familyMember) {

		try {
			familyMemberService.addFamilyMember(familyMember);
		} catch (FamilyMemberAlreadyPresentException e) {
			LOGGER.error("Member already exists", e);
			Response response = new Response(Response.Status.FAILURE, "Member Already Present");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response(Response.Status.SUCCESS, "Member Added Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteFamilyMember(@PathVariable("id") String id) {
		try {
			familyMemberService.removeFamilyMember(id);
		} catch (FamilyMemberNotFoundException exp) {
			LOGGER.error("Member Does not exists", exp);
			Response response = new Response(Response.Status.FAILURE, exp.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		Response response = new Response(Response.Status.SUCCESS, "Removed Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateFamilyMember(@PathVariable("id") String id, @RequestBody String name) {
		try {
			familyMemberService.updateFamilyMember(id, name);
		} catch (FamilyMemberNotFoundException e ) {
			LOGGER.error("Member Does not exists", e);
			Response response = new Response(Response.Status.FAILURE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch (IOException e) {
			LOGGER.error("Json Parsing Failed ", e);
			Response response = new Response(Response.Status.FAILURE, e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response(Response.Status.SUCCESS, "Updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
