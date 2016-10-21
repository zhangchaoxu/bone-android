package com.idogfooding.bone.utils;

/**
 * PermissionRequest
 *
 * @author Charles
 */
public class PermissionRequest {
    private String name;
    private boolean requested;
    private boolean granted;
    private boolean denied;
    private boolean neverAskAgain;

    public PermissionRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }

    public boolean isDenied() {
        return denied;
    }

    public void setDenied(boolean denied) {
        this.denied = denied;
    }

    public boolean isNeverAskAgain() {
        return neverAskAgain;
    }

    public void setNeverAskAgain(boolean neverAskAgain) {
        this.neverAskAgain = neverAskAgain;
    }
}
