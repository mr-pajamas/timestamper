package org.nelect.timestamper.web.service;

import org.nelect.timestamper.partner.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Michael on 2016/6/10.
 */
@RestController
@RequestMapping("/e-invoice")
public class GetEInvoiceRestController extends AbstractRestController {

    @RequestMapping(value = "/{digest}", method = RequestMethod.GET)
    public EInvoiceModel getEInvoice(@PathVariable String digest) {
        EInvoiceService eInvoiceService = sessionFactory.newSession().getEInvoiceService();

        EInvoice eInvoice = eInvoiceService.getEInvoice(digest);

        if (eInvoice == null) throw new EInvoiceNotFoundException();
        if (!eInvoice.isConfident()) throw new EInvoiceNotConfidentException();

        return new EInvoiceModel(eInvoice);
    }

    @RequestMapping(method = RequestMethod.GET, params = "certNumber")
    public EInvoiceModel getEInvoiceByCertNumber(@RequestParam String certNumber) {
        EInvoiceService eInvoiceService = sessionFactory.newSession().getEInvoiceService();

        EInvoice eInvoice = eInvoiceService.findEInvoiceByCertNumber(certNumber);

        if (eInvoice == null) throw new EInvoiceNotFoundException();
        if (!eInvoice.isConfident()) throw new EInvoiceNotConfidentException();

        return new EInvoiceModel(eInvoice);
    }

    @RequestMapping(method = RequestMethod.GET, params = "checksum")
    public EInvoiceModel getEInvoiceByChecksum(@RequestParam String checksum) {
        EInvoiceService eInvoiceService = sessionFactory.newSession().getEInvoiceService();

        EInvoice eInvoice = eInvoiceService.findEInvoiceByChecksum(checksum);

        if (eInvoice == null) throw new EInvoiceNotFoundException();
        if (!eInvoice.isConfident()) throw new EInvoiceNotConfidentException();

        return new EInvoiceModel(eInvoice);
    }

    @ExceptionHandler(EInvoiceNotFoundException.class)
    public ResponseEntity<ErrorModel> handleEInvoiceNotFoundException() {
        ErrorModel model = new ErrorModelBuilder("040800").build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }

    @ExceptionHandler(EInvoiceNotConfidentException.class)
    public ResponseEntity<ErrorModel> handleEInvoiceNotConfidentException() {
        ErrorModel model = new ErrorModelBuilder("040801").build();
        return new ResponseEntity<>(model, HttpStatus.valueOf(model.getStatusCode()));
    }
}
