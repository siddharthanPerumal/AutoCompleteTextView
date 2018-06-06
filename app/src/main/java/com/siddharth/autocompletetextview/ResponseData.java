package com.siddharth.autocompletetextview;

import java.util.ArrayList;

public class ResponseData {
    private ArrayList<Coupen> coupons;

    public ArrayList<Coupen> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupen> coupons) {
        this.coupons = coupons;
    }

    class Coupen {
        private String coupon;

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }
    }
}
