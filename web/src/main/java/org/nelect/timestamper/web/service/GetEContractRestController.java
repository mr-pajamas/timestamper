package org.nelect.timestamper.web.service;

import org.nelect.timestamper.partner.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Michael on 2016/6/10.
 */
@RestController
@RequestMapping("/e-contract")
public class GetEContractRestController extends AbstractRestController {

    @RequestMapping(value = "/{digest}", method = RequestMethod.GET)
    public EContractModel getEContract(@PathVariable String digest) {
        EContractService eContractService = sessionFactory.newSession().getEContractService();

        EContract eContract = eContractService.getEContract(digest);

        if (eContract == null) throw new EContractNotFoundException();
        if (!eContract.isConfident()) throw new EContractNotConfidentException();

        return new EContractModel(eContract);
    }

    @ExceptionHandler(EContractNotFoundException.class)
    public ResponseEntity<ErrorModel> handleEContractNotFoundException() {
        ErrorModel model = new ErrorModelBuilder("040600").build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }

    @ExceptionHandler(EContractNotConfidentException.class)
    public ResponseEntity<ErrorModel> handleEContractNotConfidentException() {
        ErrorModel model = new ErrorModelBuilder("040601").build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }
}
