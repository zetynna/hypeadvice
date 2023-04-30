package com.example.hypeadvice.domain.bean;

import com.example.hypeadvice.domain.entity.Advice;
import com.example.hypeadvice.domain.service.AdviceService;
import com.example.hypeadvice.domain.vo.AdviceListVO;
import com.example.hypeadvice.domain.vo.AdviceVO;
import com.example.hypeadvice.domain.vo.Slip;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class AdviceListBean extends Bean {

    @Autowired AdviceService adviceService;

    private Advice advice = new Advice();
    private AdviceListVO adviceListVO;
    private AdviceVO adviceVO;

    public void initBean() {
        advice = new Advice();
    }

    public void buscar() {
        try {
            this.adviceListVO = adviceService.buscar(advice);
        } catch (Exception e) {
            addMessageError(e);
        }
    }

    public void buscarPorId() {
        try {
            if(advice.getId() == null || advice.getId() == 0){
                addFaceMessage(FacesMessage.SEVERITY_ERROR, "Id não pode ser nulo!", null);
            } else {
                this.adviceListVO = new AdviceListVO();
                this.adviceVO = adviceService.buscarPorId(advice);
                List<Slip> slips = new ArrayList<>();
                slips.add(adviceVO.getSlip());
                this.adviceListVO.setSlips(slips);
            }
        } catch (Exception e) {
            addMessageError(e);
        }
    }

    public AdviceListVO getAdviceListVO() {
        return adviceListVO;
    }

    public void setAdviceListVO(AdviceListVO adviceListVO) {
        this.adviceListVO = adviceListVO;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
