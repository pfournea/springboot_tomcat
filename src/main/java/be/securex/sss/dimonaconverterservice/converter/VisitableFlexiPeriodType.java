package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.FlexiPeriodType;

/**
 * Created by 8565 on 2/11/2015.
 */
public class VisitableFlexiPeriodType implements DimonaContractVisitable {

    private final FlexiPeriodType flexiPeriodType;

    public VisitableFlexiPeriodType(FlexiPeriodType flexiPeriodType) {
        this.flexiPeriodType = flexiPeriodType;
    }

    @Override
    public void accept(DimonaContractVisitor visitor) {
        visitor.visit(flexiPeriodType);
    }
}
