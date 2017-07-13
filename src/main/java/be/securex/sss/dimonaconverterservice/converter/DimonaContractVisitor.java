package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.*;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;

/**
 * Created by 8565 on 2/11/2015.
 */
public interface DimonaContractVisitor {

    public void visit(XtraType xtraType);

    public void visit(StudentType xtraType, StudentType originalXtraType);

    void visit(FlexiDayType flexiDay);

    void visit(FlexiPeriodType flexiPeriod);

    void visit(DefiniteTermType definiteTerm);

    void visit(IndefiniteTermType indefiniteTerm, IndefiniteTermType originalIndefiniteTerm);
}
