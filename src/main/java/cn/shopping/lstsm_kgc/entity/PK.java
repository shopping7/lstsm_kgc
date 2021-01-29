package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

import java.io.Serializable;

@Data
public class PK implements Serializable {
    private static final long serialVersionUID = 1L;

    private byte[] psi1;

    private byte[] psi2;

    private Psi3_Map[] psi3;

    private byte[] psi4;

    private byte[] psi5;
}
