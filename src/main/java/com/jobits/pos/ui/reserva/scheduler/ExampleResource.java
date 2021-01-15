package com.jobits.pos.ui.reserva.scheduler;

import com.jobits.ui.scheduler.Availability;
import com.jobits.ui.scheduler.Resource;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: jgerth Date: 1/25/11 Time: 7:35 AM To change
 * this template use File | Settings | File Templates.
 */
public class ExampleResource implements Resource {

    private String _title;
    private Color _color;
    private LocalTime _startTime = LocalTime.of(9, 0, 0);
    private Duration _duration = Duration.ofHours(6);
    private boolean _takeLunch = true;

    /**
     * Create the example resource.
     *
     * @param title Title for the resource.
     * @param color Color to assign the resource.
     */
    public ExampleResource(String title, Color color) {
        _title = title;
        _color = color;
    }

    @Override

    public String getTitle() {
        return _title;
    }

    public void setStartTime(LocalTime time) {
        _startTime = time;
    }

    public LocalTime getStartTime() {
        return _startTime;
    }

    public void setDuration(Duration duration) {
        _duration = duration;
    }

    public Duration getDuration() {
        return _duration;
    }

    public void setTakeLunch(boolean truth) {
        _takeLunch = truth;
    }

    public boolean getTakeLunch() {
        return _takeLunch;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public void setColor(Color color) {
        _color = color;
    }

    @Override
    public Iterator<Availability> getAvailability(LocalDate date) {
        List<Availability> list = new ArrayList<Availability>();
        if (_takeLunch) {
            // Split it into two chunks and give them an hour lunch
            Duration seconds = _duration.minusSeconds(3600).dividedBy(2);
            Duration halfDay = seconds;
            list.add(new Availability(_startTime, halfDay));
            list.add(new Availability(_startTime.plus(seconds).plus(Duration.ofHours(1)), halfDay));
        } else {
            list.add(new Availability(_startTime, _duration));
        }
        return list.iterator();
    }

    /**
     * Get the color for our resource. This is used by the example resource
     * component to paint the component.
     *
     * @return (not null) The color to paint the component.
     */
    public Color getColor() {
        return _color;
    }

    @Override

    public String toString() {
        return _title;
    }
}
