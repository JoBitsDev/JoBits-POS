/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jobits.pos.client.webconnection.domain;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
@Data
public class Token {
    private Integer id;
    private String token;
    private LocalDateTime expira;
    
    public Token() {
    }


}



