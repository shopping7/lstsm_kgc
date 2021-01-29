package cn.shopping.lstsm_kgc.entity;

import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class DoublePairing {
   public static Pairing pairing;
   public static Field G1, GT, Zr, K;

   public void getStart(){
      pairing = PairingFactory.getPairing("application.properties");
      PairingFactory.getInstance().setUsePBCWhenPossible(true);
      if(!pairing.isSymmetric()){
         throw new RuntimeException("密钥不对称");
      }

      Zr = pairing.getZr();
      G1 = pairing.getG1();
      K = pairing.getG2();
      GT = pairing.getGT();
   }
}