package com.packtpub.pf.blueprint;

/**
 * Created with IntelliJ IDEA.
 * User: psramkumar
 * Date: 3/21/14
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public enum JobStatus {
    SUBMITTED("SUBMITTED"), REJECTED("REJECTED"), CANCELED("CANCELED"), PROCESS("PROCESS"), COMPLETED("COMPLETED");


    private final String name;

    JobStatus(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
