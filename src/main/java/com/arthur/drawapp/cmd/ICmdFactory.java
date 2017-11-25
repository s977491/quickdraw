package com.arthur.drawapp.cmd;

/**
 * Created by laia on 11/25/2017.
 */
public interface ICmdFactory {

    ICmd createCmd(String sCmd);
}
