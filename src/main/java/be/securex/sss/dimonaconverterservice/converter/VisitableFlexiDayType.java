package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.FlexiDayType;

/**
 * Created by 8565 on 2/11/2015.
 */
public class VisitableFlexiDayType implements DimonaContractVisitable {

    private final FlexiDayType flexiDayType;

    public VisitableFlexiDayType(FlexiDayType flexiDayType) {
        this.flexiDayType = flexiDayType;
    }

    @Override
    public void accept(DimonaContractVisitor visitor) {
        visitor.visit(flexiDayType);
    }
}
