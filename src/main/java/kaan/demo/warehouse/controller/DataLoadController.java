package kaan.demo.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kaan.demo.warehouse.dto.JsonDto;
import kaan.demo.warehouse.service.DataLoader;

/**
 * Endpoint for handling requests for data store from json
 * 
 * @author Kaan Bobac
 *
 */
@Controller
public class DataLoadController {
	@Autowired
	DataLoader dataLoader;

	@PostMapping("/save-all")
	public ResponseEntity saveJsonToDatabase(@RequestBody JsonDto jsonDetail) {
		return new ResponseEntity<>(dataLoader.saveDataFromJson(jsonDetail), HttpStatus.OK);
	}
}
