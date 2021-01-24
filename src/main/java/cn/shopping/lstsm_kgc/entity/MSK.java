package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

import java.io.Serializable;


@Data
public class MSK implements Serializable {
    private static final long serialVersionUID = 1L;

    private byte[] alpha;

    private byte[] lambda;

    private byte[] tau;

    private byte[] k1;

    private byte[] k2;
}
