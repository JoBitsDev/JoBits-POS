/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.licencia;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class Cipher {

        private final String key;

        private static final String ALGORITHM = "AES";

        public Cipher(String key) {
            this.key = key;
        }

        public String decrypt(String cipherText) throws Exception {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(ALGORITHM);
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey);

            return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(cipherText)));
        }
    }

 

