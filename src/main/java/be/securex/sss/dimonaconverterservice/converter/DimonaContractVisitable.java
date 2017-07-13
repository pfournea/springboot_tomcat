package be.securex.sss.dimonaconverterservice.converter;

/**
 * Created by 8565 on 2/11/2015.
 */
public interface DimonaContractVisitable {

    public void accept(DimonaContractVisitor visitor);
}
