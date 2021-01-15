package com.jobits.pos.ui.reserva.scheduler;

import com.jobits.ui.scheduler.Resource;

public interface ResourceListener {

    void handleDelete(Resource resource);

    void handleEdit(Resource resource);
}
