/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.logs;

import com.jobits.pos.adapters.repo.impl.OrdenLogRepo;
import com.jobits.pos.main.Application;
import com.jobits.pos.recursos.R;
import java.util.List;

/**
 *
 * @author Home
 */
public class OrdenLogController implements OrdenLogService {

    public OrdenLogController() {
    }

    @Override
    public void saveToLogFile(String value) {
        OrdenLogRepo.saveToLogFile(value);
    }

    @Override
    public List<String[]> loadLogFile(String keyWord) {
        Application.getInstance().authorizeUser(R.NivelAcceso.ECONOMICO);
        return OrdenLogRepo.loadLogFile(keyWord);
    }
}
