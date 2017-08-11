package com.vitaltechlabs.findhostels.serverapis;

/**
 * Referral int for url call
 * <p>
 * Created by RuchiTiwari on 2/12/2017.
 */
public enum ApiRequestReferralCode {

    LOGIN(1),
    BedAvailabilityUrl(2),
    CHANGEPASSWORD(3),
    FOODMENU(4),
    BEDPRICE(5),
    DELETEHOSTEL(6),
    EDITHOSTEL(7),
    FACILITIES(8),
    EDITBEDPRICE(9);

    private int code;

    ApiRequestReferralCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + "";
    }
}
