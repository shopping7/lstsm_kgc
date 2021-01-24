package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

import java.io.Serializable;


@Data
public class PP implements Serializable {
    private static final long serialVersionUID = 1L;

    private byte[] g;

    private byte[] h;

    private byte[] f;

    private byte[] Y;

    private byte[] Y0;
}
