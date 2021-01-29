package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

import java.io.Serializable;


@Data
public class CT implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte[] C0;
    private byte[] C1;
    private byte[] C2;
    private C3_Map[] C3;
    private byte[][] C3_1;
    private byte[] C4;
    private byte[] Cm;
}
