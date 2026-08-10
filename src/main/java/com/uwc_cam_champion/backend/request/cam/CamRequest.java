package com.uwc_cam_champion.backend.request.cam;
//import com.uwc_cam_champion.backend.request.cam.CamRequest;

import java.math.BigDecimal;

public class CamRequest {

    private BigDecimal actualCam;
    private BigDecimal targetCam;
    private BigDecimal projectedCam;

    public CamRequest() {
    }

    public BigDecimal getActualCam() {
        return actualCam;
    }

    public void setActualCam(BigDecimal actualCam) {
        this.actualCam = actualCam;
    }

    public BigDecimal getTargetCam() {
        return targetCam;
    }

    public void setTargetCam(BigDecimal targetCam) {
        this.targetCam = targetCam;
    }

    public BigDecimal getProjectedCam() {
        return projectedCam;
    }

    public void setProjectedCam(BigDecimal projectedCam) {
        this.projectedCam = projectedCam;
    }
}