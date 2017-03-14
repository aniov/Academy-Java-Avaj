package com.company.service.md5encryptor;

import com.company.entity.AirCraftType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Marius on 3/10/2017.
 */
public class EncryptMD5 {

    public String getIntegerValues(String input) {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String nr = encryptToMD5(i + "");
            if (nr.equals(input)) {
                return String.valueOf(i);
            }
        }
        return "-1";
    }

    public String getTypeValue(String type) {

        for (int i = 0; i < AirCraftType.values().length; i++){
            String airType = encryptToMD5(AirCraftType.values()[i].getType());
            if (airType.equals(type)){
                return AirCraftType.values()[i].getType();
            }
        }
        return "-1";
    }

    public String getNameValue(String name, String type){

        for (long i = 0; i < Long.MAX_VALUE; i++) {
            String nr = encryptToMD5(type.charAt(0) + "" + i);
            if (nr.equals(name)) {
                return type.charAt(0) + "" + i;
            }
        }
        return "-1";
    }

    private String encryptToMD5(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());

            byte data[] = md.digest();

            for (int i = 0; i < data.length; i++) {
                stringBuilder.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
