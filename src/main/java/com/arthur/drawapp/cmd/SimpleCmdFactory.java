package com.arthur.drawapp.cmd;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by laia on 11/25/2017.
 */
public class SimpleCmdFactory extends AbstractCmdFactory {

    @Inject
    Provider<CmdBucketFill> cmdBucketFillProvider;
    @Inject
    Provider<CmdCreateCanvas> cmdCreateCanvasProvider;
    @Inject
    Provider<CmdDrawLine> cmdDrawLineProvider;
    @Inject
    Provider<CmdDrawRect> cmdDrawRectProvider;
    @Inject
    Provider<CmdQuit> cmdQuitProvider;

    @Override
    public ICmd createCmd(String sCmd) {
        String[] tokens = sCmd.split(" ");
        if (tokens.length< 1)
            return null;

        try {
            if (tokens[0].equals("C")) {
                if (tokens.length != 3)
                    return null;
                int w = Integer.parseInt(tokens[1]);
                int h = Integer.parseInt(tokens[2]);

                CmdCreateCanvas cmd = cmdCreateCanvasProvider.get();
                cmd.setHeight(h);
                cmd.setWidth(w);
                return cmd;
            } else if (tokens[0].equals("L")) {
                if (tokens.length != 5)
                    return null;
                int x1 = Integer.parseInt(tokens[1]);
                int y1 = Integer.parseInt(tokens[2]);
                int x2 = Integer.parseInt(tokens[3]);
                int y2 = Integer.parseInt(tokens[4]);

                CmdDrawLine cmd = cmdDrawLineProvider.get();
                cmd.setX1(x1);
                cmd.setY1(y1);
                cmd.setX2(x2);
                cmd.setY2(y2);
                return cmd;
            } else if (tokens[0].equals("R")) {
                if (tokens.length != 5)
                    return null;
                int x1 = Integer.parseInt(tokens[1]);
                int y1 = Integer.parseInt(tokens[2]);
                int x2 = Integer.parseInt(tokens[3]);
                int y2 = Integer.parseInt(tokens[4]);

                CmdDrawRect cmd = cmdDrawRectProvider.get();
                cmd.setX1(x1);
                cmd.setY1(y1);
                cmd.setX2(x2);
                cmd.setY2(y2);
                return cmd;
            } else if (tokens[0].equals("B")) {
                if (tokens.length != 4)
                    return null;
                int x = Integer.parseInt(tokens[1]);
                int y = Integer.parseInt(tokens[2]);
                if (tokens[3].length() != 1){
                    return null;
                }

                CmdBucketFill cmd = cmdBucketFillProvider.get();
                cmd.setX(x);
                cmd.setY(y);
                cmd.setColor(tokens[3].charAt(0));
                return cmd;
            } else if (tokens[0].equals("Q")) {
                if (tokens.length != 1)
                    return null;
                CmdQuit cmd = cmdQuitProvider.get();
                return cmd;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }
}
