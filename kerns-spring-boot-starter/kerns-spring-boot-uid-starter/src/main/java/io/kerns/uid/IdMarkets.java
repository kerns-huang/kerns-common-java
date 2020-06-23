package io.kerns.uid;

/**
 * @author xiaohei
 * @create 2020-06-23 下午4:33
 **/
public class IdMarkets {
    private static IdMarket idMarket;

    static void setIdMarket(IdMarket idMarket) {
        IdMarkets.idMarket = idMarket;
    }

    public static long nextId(Integer businessType) {
        return idMarket.nextId(businessType);
    }
}
