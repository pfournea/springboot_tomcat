package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.XtraType;

/**
 * Created by 8565 on 2/11/2015.
 */
public class VisitableXtraType implements DimonaContractVisitable {

    private final XtraType xtraType;

    public VisitableXtraType(XtraType xtraType) {
        this.xtraType = xtraType;
    }

    @Override
    public void accept(DimonaContractVisitor visitor) {
        visitor.visit(xtraType);
    }
}
