package be.securex.sss.dimonaconverterservice.validator;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractEvent;

/**
 * Created by 6148 on 20/10/2015.
 */
public interface ContractEventValidator {
    boolean validate(ContractEvent contractEvent) throws Exception;
}
