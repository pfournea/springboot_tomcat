package be.securex.sss.dimonaconverterservice.controller;

import be.securex.sss.dimonaconverterservice.MediaTypeRepository;
import be.securex.sss.dimonaconverterservice.converter.ContractEventToHireConverter;
import be.securex.sss.dimonaconverterservice.converter.ContractEventToXeiDimConverter;
import be.securex.sss.dimonaconverterservice.converter.ContractWijzigingToContractEventConverter;
import be.securex.sss.dimonaconverterservice.dto.*;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.XeiDimList;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.HireList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 6060 on 14/10/2015.
 */
@Api(value = "converter" , description = "Manage the creation and conversion of a ContractEvent")
@RestController
@RequestMapping(value = "/api/convert")
public class ConverterController {

    @Autowired
    ContractWijzigingToContractEventConverter contractWijzigingToContractEventConverter;

    @Autowired
    ContractEventToXeiDimConverter contractEventToXeiDimConverter;

    @Autowired
    ContractEventToHireConverter contractEventToHireConverter;

    @ApiOperation(value = "Create a ContractEvent - create",  httpMethod = "POST")
    @RequestMapping(value = "/contractcreationevent",
            consumes = MediaTypeRepository.CONTRACTEVENT_MEDIA_TYPE_STRING,
            produces = MediaType.APPLICATION_XML_VALUE,
            method = RequestMethod.POST)
    @ApiResponses( value =  {
            @ApiResponse(code = 200, message = "Success", response = ContractEvent.class),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ContractEvent convertContractCreationEvent(@RequestBody ContractCreationDto contractCreation) throws Exception {
        ContractEvent contractEvent = contractWijzigingToContractEventConverter.convert(contractCreation);
        return contractEvent;
    }

    @ApiOperation(value = "Create a ContractEvent - update",  httpMethod = "POST")
    @RequestMapping(value = "/contractupdateevent",
            consumes = MediaTypeRepository.CONTRACTEVENT_MEDIA_TYPE_STRING,
            produces = MediaType.APPLICATION_XML_VALUE,
            method = RequestMethod.POST)
    @ApiResponses( value =  {
            @ApiResponse(code = 200, message = "Success", response = ContractEvent.class),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ContractEvent convertContractUpdateEvent(@RequestBody ContractUpdateDto contractUpdate) throws Exception {
        ContractEvent contractEvent = contractWijzigingToContractEventConverter.convert(contractUpdate);
        return contractEvent;
    }

    @ApiOperation(value = "Create a ContractEvent - cancel",  httpMethod = "POST")
    @RequestMapping(value = "/contractcancellationevent",
            consumes = MediaTypeRepository.CONTRACTEVENT_MEDIA_TYPE_STRING,
            produces = MediaType.APPLICATION_XML_VALUE,
            method = RequestMethod.POST)
    @ApiResponses( value =  {
            @ApiResponse(code = 200, message = "Success", response = ContractEvent.class),
            @ApiResponse(code = 500, message = "Failure")
    })
    public ContractEvent convertContractUpdateEvent(@RequestBody ContractCancellationDto contractCancellation) throws Exception {
        ContractEvent contractEvent = contractWijzigingToContractEventConverter.convert(contractCancellation);
        return contractEvent;
    }

    @ApiOperation(value = "Convert a ContractEvent to a XeiDim",  httpMethod = "POST")
    @RequestMapping(value = "/xeidim",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ApiResponses( value =  {
            @ApiResponse(code = 200, message = "Success", response = XeiDimList.class),
            @ApiResponse(code = 500, message = "Failure")
    })
    public XeiDimList convertXeiDim(@RequestBody ContractEvent contractEvent) throws Exception {
        return contractEventToXeiDimConverter.convert(contractEvent);
    }

    @ApiOperation(value = "Convert a ContractEvent to a NGA Mini Hire xml",  httpMethod = "POST")
    @RequestMapping(value = "/hire",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ApiResponses( value =  {
            @ApiResponse(code = 200, message = "Success", response = HireList.class),
            @ApiResponse(code = 500, message = "Failure")
    })
    public HireList convertHires(@RequestBody ContractEvent contractEvent) throws Exception {
        return contractEventToHireConverter.convert(contractEvent);
    }



}
