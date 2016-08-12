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
@RequestMapping("/timestamp/e-invoice")
public class TimestampEInvoiceRestController extends AbstractRestController {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<EInvoiceModel> timestampEInvoice(@Valid EInvoiceForm eInvoiceForm) {

        EInvoiceService eInvoiceService = sessionFactory.newSession().getEInvoiceService();

        EInvoice eInvoice = eInvoiceService.timestampEInvoice(eInvoiceForm);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(MvcUriComponentsBuilder.fromMethodCall(
            on(this.getClass()).getResult(eInvoice.getDigest())).buildAndExpand().toUri());
        return new ResponseEntity<>(new EInvoiceModel(eInvoice), headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{digest}", method = RequestMethod.GET)
    public ResponseEntity<EInvoiceModel> getResult(@PathVariable String digest) {

        EInvoiceService eInvoiceService = sessionFactory.newSession().getEInvoiceService();

        EInvoice eInvoice = eInvoiceService.getEInvoice(digest);

        if (eInvoice == null) throw new EInvoiceNotFoundException();

        if (eInvoice.isConfident()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(MvcUriComponentsBuilder.fromMethodCall(
                on(GetEInvoiceRestController.class).getEInvoice(digest)).buildAndExpand().toUri());
            return new ResponseEntity<>(new EInvoiceModel(eInvoice), headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<EInvoiceModel>(new EInvoiceModel(eInvoice), HttpStatus.OK);
        }
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleBindException(BindException be) {
        ErrorModelBuilder errorModelBuilder = new ErrorModelBuilder("000800");

        for (FieldError fieldError : be.getBindingResult().getFieldErrors()) {
            String errorCode = fieldError.getDefaultMessage();
            if (ErrorModelBuilder.validateErrorCode(errorCode))
                errorModelBuilder.addChildError(errorCode);
        }

        ErrorModel model = errorModelBuilder.build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }

    @ExceptionHandler(EInvoiceNotFoundException.class)
    public ResponseEntity<ErrorModel> handleEInvoiceNotFoundException() {
        ErrorModel model = new ErrorModelBuilder("040800").build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }
}
