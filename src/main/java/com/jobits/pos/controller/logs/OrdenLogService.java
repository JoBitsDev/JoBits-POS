/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.logs;

import java.util.List;

/**
 *
 * @author Home
 */
public interface OrdenLogService {

    List<String[]> loadLogFile(String keyWord);

    void saveToLogFile(String value);
    
}
