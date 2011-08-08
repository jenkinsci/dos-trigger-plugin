package com.tombrus.hudson.dostrigger;

import antlr.ANTLRException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;

/**
 * For backward compatibility loading data from older versions of this plugin.
 */
public final class DosTrigger extends org.jenkinsci.plugins.dostrigger.DosTrigger {
    public DosTrigger(String schedule, String script) throws ANTLRException {
        super(schedule, script);
    }

    protected Object readResolve() throws ObjectStreamException {
        try {
            return new org.jenkinsci.plugins.dostrigger.DosTrigger(
                getSchedule(), getScript());
        } catch (ANTLRException e) {
            InvalidObjectException x = new InvalidObjectException(e.getMessage());
            x.initCause(e);
            throw x;
        }
    }
}
