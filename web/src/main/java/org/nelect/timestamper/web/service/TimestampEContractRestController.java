package org.nelect.timestamper.web.service;

import javax.validation.Valid;

import org.nelect.timestamper.partner.*;
import org.springframework.http.*;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

/**
 * Created by Michael on 2016/6/9.
 */
@RestController
@RequestMapping("/timestamp/e-contract")
public class TimestampEContractRestController extends AbstractRestController {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<EContractModel> timestampEContract(@Valid EContractForm eContractForm) {

        EContractService eContractService = sessionFactory.newSession().getEContractService();

        EContract eContract = eContractService.timestampEContract(eContractForm);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(MvcUriComponentsBuilder.fromMethodCall(
            on(this.getClass()).getResult(eContract.getDigest())).buildAndExpand().toUri());
        return new ResponseEntity<>(new EContractModel(eContract), headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{digest}", method = RequestMethod.GET)
    public ResponseEntity<EContractModel> getResult(@PathVariable String digest) {

        EContractService eContractService = sessionFactory.newSession().getEContractService();

        EContract eContract = eContractService.getEContract(digest);

        if (eContract == null) throw new EContractNotFoundException();

        if (eContract.isConfident()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(MvcUriComponentsBuilder.fromMethodCall(
                on(GetEContractRestController.class).getEContract(digest)).buildAndExpand().toUri());
            return new ResponseEntity<>(new EContractModel(eContract), headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<EContractModel>(new EContractModel(eContract), HttpStatus.OK);
        }
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleBindException(BindException be) {
        ErrorModelBuilder errorModelBuilder = new ErrorModelBuilder("000600");

        for (FieldError fieldError : be.getBindingResult().getFieldErrors()) {
            String errorCode = fieldError.getDefaultMessage();
            if (ErrorModelBuilder.validateErrorCode(errorCode))
                errorModelBuilder.addChildError(errorCode);
        }

        ErrorModel model = errorModelBuilder.build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }

    @ExceptionHandler(EContractNotFoundException.class)
    public ResponseEntity<ErrorModel> handleEContractNotFoundException() {
        ErrorModel model = new ErrorModelBuilder("040600").build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }
}
