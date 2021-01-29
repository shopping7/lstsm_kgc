package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

import java.io.Serializable;


@Data
public class SK implements Serializable {

    private static final long serialVersionUID = 1L;

    private byte[] D1;

    private byte[] D2;

    private D3_Map[] D3;

    private byte[] D4;

    private byte[] s_1;

    private byte[] s_11;

    private byte[] u_1;

    private byte[] u_11;

    private byte[] u_111;

    private String delta_s;

    private byte[] theta;
}
