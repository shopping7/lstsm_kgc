package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

import java.io.Serializable;

@Data
public class CTout implements Serializable {
    private static final long serialVersionUID = 1L;
    private Element C0;
    private Element L1;
    private Element V1;
    private byte[] Cm;
}
